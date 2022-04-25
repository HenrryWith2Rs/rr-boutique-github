package com.example.ecommerce.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="items")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class Item implements Serializable {
    @Id
    @Column(name="pid")
    private int id;
    @Column(name="ppicture")
    private String picture;
    @Column(name="pname")
    private String name;
    @Column(name="pprice")
    private double price;
    @Column(name="pdescription")
    private String description;
    @Column(name="pcategory")
    private String category;

    public static Item createItem(int id, String picture, String name, double price, String description, String category) {
        return new Item(id, picture, name, price, description, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
