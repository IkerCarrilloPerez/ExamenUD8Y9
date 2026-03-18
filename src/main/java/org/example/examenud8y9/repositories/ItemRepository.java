package org.example.examenud8y9.repositories;

import org.example.examenud8y9.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByCategory(String category);
    Optional<Item> findByEAN(String ean);
}