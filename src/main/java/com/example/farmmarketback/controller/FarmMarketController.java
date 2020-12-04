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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private UploadService uploadService;

    @CrossOrigin
    @PostMapping("photos/upload")
    public void uploadPhoto(@RequestParam (value = "photos") MultipartFile file) throws IOException {
         byte [] a = file.getBytes();
         uploadService.uploadFile(a);
                // teha tabel 'file; with column 'bytea'
    }

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
    public Seller1 getSeller(Authentication authentication){
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        return getSellerService.getSeller(sellerId);
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
    @PutMapping("updateSeller")
    public PopUpWindow updateSeller(@RequestBody Seller_entity seller) {
        farmMarketService.updateSeller(seller.getId(), seller.getName(), seller.getEmail(), seller.getAddress(), seller.getPhone(), seller.getPersonalInformation());
        return new PopUpWindow("Your info changed.");
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
