package com.example.ecommerce.model;

import com.example.ecommerce.model.entity.Item;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {

        Item item = Item.createItem(
                9,
                "https:/298863/www.pexels.com/photo/pair-of-brown-leather-casual-shoes-on-table-",
                "Casual Brown Leather Bangers",
                84.99,
                "There is something so classy about a clean pair of brown leather casual shoes. Pair it with this button up for maximum drip.",
                "men"
        );

        System.out.println(item);
    }
}
