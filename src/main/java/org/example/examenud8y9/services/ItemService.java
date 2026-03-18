package org.example.examenud8y9.services;

import lombok.AllArgsConstructor;
import org.example.examenud8y9.entities.Item;
import org.example.examenud8y9.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemByEAN(String ean) {
        return itemRepository.findByEAN(ean);
    }
}