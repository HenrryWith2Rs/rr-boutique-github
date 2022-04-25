package com.example.ecommerce.model.service;
import com.example.ecommerce.model.entity.Item;
import com.example.ecommerce.model.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepo;

    // Create
    @Override
    public Item saveItem(Item item) {
        return itemRepo.save(item);
    }

    // Read
    @Override
    public List<Item> getAllItems() {
        return (List<Item>) itemRepo.findAll();
    }

    @Override
    public Item getItemByID(int id) {
        return itemRepo.findById(id).get();
    }

    // Update
    @Override
    public Item updateItem(Item employee, int id) {
        return null;
    }

    // Delete
    @Override
    public void deleteItemById(int id) {
        itemRepo.deleteById(id);
    }
}
