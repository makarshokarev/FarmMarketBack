package com.example.farmmarketback.controller;

import com.example.farmmarketback.exception.ApplicationException;
import com.example.farmmarketback.objects.*;
import com.example.farmmarketback.repository.CategoryRepository;
import com.example.farmmarketback.repository.ProductRepository;
import com.example.farmmarketback.repository.SellerRepository;
import com.example.farmmarketback.security.MyUser;
import com.example.farmmarketback.service.*;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GetSellerService getSellerService;

    @CrossOrigin
    @PostMapping("newSeller")
    public PopUpWindow newSeller(@RequestBody Seller_entity seller) {
        String rawPassword = seller.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getUsername(), encodedPassword, seller.getPhone());
        return new PopUpWindow("Thank you for registration.");
    }

    @CrossOrigin
    @GetMapping("getSeller")
    public Seller1 getSeller(){
        return getSellerService.getSeller();
    }

    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct(Authentication authentication, @RequestBody Product product) {
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        farmMarketService.newProduct(sellerId, product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("You have added new product: " + product.getProductName());
    }

    @GetMapping("category")
    @CrossOrigin
    public List<Category> getAccount() {
        return categoryService.getCategory();
    }

    @CrossOrigin
    @GetMapping("product")
    public List<Product> getProduct(Authentication authentication) {
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        return farmMarketService.getProductBySeller(sellerId);
    }

    @CrossOrigin
    @PutMapping("updateSellerName")
    public void updateSellerName(@RequestBody Seller1 seller) {
        farmMarketService.updateSellerName(seller.getId(), seller.getName());
    }

    @CrossOrigin
    @PutMapping("updateSellerEmail")
    public void updateSellerEmail(@RequestBody Seller_entity seller) {
        farmMarketService.updateSellerEmail(seller.getId(), seller.getEmail());
    }

    @CrossOrigin
    @PutMapping("updateSellerUsername")
    public void updateSellerUsername(@RequestBody Seller_entity seller) {
        farmMarketService.updateSellerUsername(seller.getId(), seller.getUsername());
    }

    @CrossOrigin
    @PutMapping("updateSellerPersonalInformation")
    public void updateSellerPersonalInformation(@RequestBody Seller_entity seller) {
        farmMarketService.updateSellerPersonalInformation(seller.getId(), seller.getPersonalInformation());
    }

    @CrossOrigin
    @PutMapping("updateSellerAddress")
    public void updateSellerAddress(@RequestBody Seller_entity seller) {
        farmMarketService.updateSellerAddress(seller.getId(), seller.getAddress());
    }

    @CrossOrigin
    @PutMapping("updateSellerPhone")
    public void updateSellerPhone(@RequestBody Seller_entity seller) {
        farmMarketService.updateSellerPhone(seller.getId(), seller.getPhone());
    }

    @CrossOrigin
    @PutMapping("updateSellerPassword")
    public PopUpWindow updateSellerPassword(@RequestBody Seller_entity seller) {
        String rawPassword = seller.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        farmMarketService.updateSellerPassword(seller.getName(), seller.getUsername(), seller.getEmail(), encodedPassword);
        return new PopUpWindow("Your Password is updated");
    }

    @CrossOrigin
    @GetMapping("getAllProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("getAllSellers")
    public List<Seller_entity> getAllSellers() {
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


    @CrossOrigin
    @PostMapping("login")
    public String login(@RequestBody Login login) {
        return farmMarketService.login(login);
    }
    @CrossOrigin
    @PutMapping("updateProduct")
    public PopUpWindow updateProduct(@RequestBody Product product){
        farmMarketService.updateProduct(product.getId(), product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("Thank you. Product information is updated.");
    }

    @CrossOrigin
    @GetMapping("searchProduct")
    public List <Product> searchProduct (String searchWord) {
        return farmMarketService.searchProduct(searchWord);
    }


}
