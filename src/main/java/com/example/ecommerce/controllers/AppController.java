package com.example.ecommerce.controllers;

import com.example.ecommerce.model.entity.Item;
import com.example.ecommerce.model.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AppController {
    private int globalID = 1;

    private final ItemRepository itemRepository;

    public AppController(ItemRepository repository){
        this.itemRepository = repository;
    }

    // NAVIGATION
    @GetMapping(value = "/MDB-Default") public String getMDBDefault() { return "MDB-Default"; }
    @GetMapping(value = "/cart")        public String getCart() { return "cart"; }
    @GetMapping(value = "/product-new") public String getNewProduct() { return "product-new"; }

    // ----- CREATE -----
    // all fields requested
    @PostMapping(value ="/saveProduct")
    public String saveOneItem(@ModelAttribute Item item)
    {
        // if ID in place, show error in console and redirect
        if(itemRepository.existsById(item.getId())){
            System.out.println("Error, product ID already in use.");
            return "redirect:/productFailure";
        }

        // implied else
        itemRepository.save(item); // product saved via JPA repository
        globalID = item.getId(); // used in redirect to show the latest product
        return "redirect:/productSuccess";
    }

    // Item was saved, redirect and show new item
    @GetMapping(value = "/productSuccess")
    public String showProductSaved(Model model) {
        model.addAttribute("productByID", itemRepository.getById(globalID));
        return "product";
    }

    // Item was NOT saved, redirect and show form again
    @GetMapping(value = "/productFailure")
    public String showProductFail() {
        return "product-new";
    }

    // ----- READ -----
    // get all items
    @GetMapping(value = {"/", "/all"})
    public String getAllItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "all";
    }

    @GetMapping(value = { "/all/{page}"})
    public String getAllItems(
            Model model,
            @PathVariable Optional<Integer> page,
            @PathVariable Optional<String> sortBy) {

        Page<Item> products = itemRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        6,
                        Sort.Direction.ASC, sortBy.orElse("id")
                )
        );

        model.addAttribute("items", products);
        return "all";
    }

    // get requested item
    @GetMapping(value = "/product/{id}")
    public String getOneItem(@PathVariable int id, Model model) {
        model.addAttribute("productByID", itemRepository.getById(id));
        return "product";
    }

    // get by category
    @GetMapping("/{category}")
    public String getCategory(@PathVariable String category, Model model){
        model.addAttribute("items", itemRepository.findByCategory(category));
        return "all";
    }

    // ----- UPDATE -----
    @GetMapping(value = "/form/{id}")
    public String populateFields(@PathVariable int id, Model model) {
        model.addAttribute("productByID", itemRepository.getById(id));
        return "product-update";
    }

    @PostMapping(value = "/updateProduct")
    public String updateItem(@ModelAttribute Item item)
    {
        itemRepository.deleteById(item.getId());
        itemRepository.save(item); // product saved via JPA repository
        globalID = item.getId(); // used in redirect to show the latest product
        return "redirect:/productSuccess";
    }

    // ----- DELETE -----
    // Delete single item
    @GetMapping(value = "/delete/{id}")
    public String deleteOneItem(@PathVariable int id) {
        itemRepository.deleteById(id);
        return "redirect:/all";
    }
}