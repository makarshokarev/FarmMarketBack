package com.example.FarmMarket.controller;



import com.example.FarmMarket.objects.Category;
import com.example.FarmMarket.objects.Seller;
import com.example.FarmMarket.repository.CategoryRepository;
import com.example.FarmMarket.repository.ProductRepository;
import com.example.FarmMarket.repository.SellerRepository;
import com.example.FarmMarket.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import com.example.FarmMarket.objects.PopUpWindow;
import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.service.FarmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @CrossOrigin
    @PostMapping("newSeller")
    public PopUpWindow newSeller (@RequestBody Seller seller) {
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getUsername(), seller.getPassword(), seller.getPhone());
        return new PopUpWindow("Thank you for registration.");
    }

    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct (@RequestBody Product product) {
        farmMarketService.newProduct(product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("You have added new product: " + product.getProductName());
    }


    @GetMapping("category")
    @CrossOrigin
    public List<Category> getAccount() {
        List<Category> result = categoryService.getCategory();
        return result;
    }

    @PutMapping("updateSellerName")
    public void updateSellerName(@RequestBody Seller seller){
        farmMarketService.updateSellerName(seller.getId(), seller.getName());
    }
    @CrossOrigin
    @PutMapping("updateSellerEmail")
    public void updateSellerEmail(@RequestBody Seller seller){
        farmMarketService.updateSellerEmail(seller.getId(), seller.getEmail());
    }
    @CrossOrigin
    @PutMapping("updateSellerUsername")
    public void updateSellerUsername(@RequestBody Seller seller){
        farmMarketService.updateSellerUsername(seller.getId(), seller.getUsername());
    }

    @CrossOrigin
    @PutMapping("updateSellerPersonalInformation")
    public void updateSellerPersonalInformation(@RequestBody Seller seller){
        farmMarketService.updateSellerPersonalInformation(seller.getId(), seller.getPersonalInformation());
    }
    @CrossOrigin
    @PutMapping("updateSellerAddress")
    public void updateSellerAddress(@RequestBody Seller seller){
        farmMarketService.updateSellerAddress(seller.getId(), seller.getAddress());
    }
    @CrossOrigin
    @PutMapping("updateSellerPhone")
    public void updateSellerPhone(@RequestBody Seller seller){
        farmMarketService.updateSellerPhone(seller.getId(), seller.getPhone());
    }
    @CrossOrigin
    @PutMapping("updateSellerPassword")
    public PopUpWindow updateSellerPassword(@RequestBody Seller seller){
        farmMarketService.updateSellerPassword(seller.getName(), seller.getUsername(), seller.getEmail(),seller.getPassword());
        return new PopUpWindow("Your Password is updates");
    }

    @CrossOrigin
    @GetMapping("getAllProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @CrossOrigin
    @GetMapping("getAllSellers")
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("getAllCategories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("getLatestProducts")
    public List<Product> getLatest() {
        return farmMarketService.getLatest();
    }


}
