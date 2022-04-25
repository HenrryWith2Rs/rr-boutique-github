package com.example.ecommerce.model.service;

import com.example.ecommerce.model.entity.Item;

import java.util.List;

public interface ItemService {
    // save
    Item saveItem(Item item);

    // read
    List<Item> getAllItems();
    Item getItemByID(int id);

    // update
    Item updateItem(Item employee, int id);

    // Delete
    void deleteItemById(int id);
}
