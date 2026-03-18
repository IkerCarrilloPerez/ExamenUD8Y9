package org.example.examenud8y9.controllers;

import lombok.AllArgsConstructor;
import org.example.examenud8y9.entities.Item;
import org.example.examenud8y9.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ViewController {
    private final ItemService itemService;

    @GetMapping
    public String index(Model model) {
        List<Item> items = itemService.getAllItems();
        List<String> categories = items.stream()
                .map(Item::getCategory)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("items", items);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/items")
    public String listItems(@RequestParam(required = false) String category, Model model) {
        List<Item> items = category == null || category.isEmpty() ?
                itemService.getAllItems() :
                itemService.getItemsByCategory(category);
        List<String> categories = itemService.getAllItems().stream()
                .map(Item::getCategory)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("items", items);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        return "items";
    }

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable String id, Model model) {
        return itemService.getItemById(id)
                .map(item -> {
                    model.addAttribute("item", item);
                    return "detail";
                })
                .orElse("redirect:/items");
    }

    @GetMapping("/item/ean/{ean}")
    public String itemDetailByEAN(@PathVariable String ean) {
        return itemService.getItemByEAN(ean)
                .map(item -> "redirect:/item/" + item.getId())
                .orElse("redirect:/items");
    }

    @GetMapping("/item/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        return itemService.getItemById(id)
                .map(item -> {
                    List<String> categories = itemService.getAllItems().stream()
                            .map(Item::getCategory)
                            .distinct()
                            .collect(Collectors.toList());
                    model.addAttribute("item", item);
                    model.addAttribute("categories", categories);
                    return "edit";
                })
                .orElse("redirect:/items");
    }

    @PostMapping("/item/{id}/edit")
    public RedirectView editItem(@PathVariable String id, @ModelAttribute Item item) {
        item.setId(id);
        itemService.saveItem(item);
        return new RedirectView("/item/" + id);
    }
}