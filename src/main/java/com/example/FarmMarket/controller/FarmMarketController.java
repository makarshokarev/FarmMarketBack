package com.example.FarmMarket.controller;

import com.example.FarmMarket.exception.ApplicationException;
import com.example.FarmMarket.objects.*;
import com.example.FarmMarket.repository.CategoryRepository;
import com.example.FarmMarket.repository.ProductRepository;
import com.example.FarmMarket.repository.SellerRepository;
import com.example.FarmMarket.service.CategoryService;
import com.example.FarmMarket.service.LoginService;
import com.example.FarmMarket.service.ProductService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.FarmMarket.service.FarmMarketService;
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
    private LoginService loginService;

    @CrossOrigin
    @PostMapping("newSeller")
    public PopUpWindow newSeller(@RequestBody Seller seller) {
        String rawPassword = seller.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getUsername(), encodedPassword, seller.getPhone());
        return new PopUpWindow("Thank you for registration.");
    }

    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct(@RequestBody Product product) {
        farmMarketService.newProduct(product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("You have added new product: " + product.getProductName());
    }

    @GetMapping("category")
    @CrossOrigin
    public List<Category> getAccount() {
        List<Category> result = categoryService.getCategory();
        return result;
    }

    @CrossOrigin
    @GetMapping("product")
    public List<Product> getProduct() {
        List<Product> result = productService.getProduct();
        return result;
    }

    @PutMapping("updateSellerName")
    public void updateSellerName(@RequestBody Seller seller) {
        farmMarketService.updateSellerName(seller.getId(), seller.getName());
    }

    @CrossOrigin
    @PutMapping("updateSellerEmail")
    public void updateSellerEmail(@RequestBody Seller seller) {
        farmMarketService.updateSellerEmail(seller.getId(), seller.getEmail());
    }

    @CrossOrigin
    @PutMapping("updateSellerUsername")
    public void updateSellerUsername(@RequestBody Seller seller) {
        farmMarketService.updateSellerUsername(seller.getId(), seller.getUsername());
    }

    @CrossOrigin
    @PutMapping("updateSellerPersonalInformation")
    public void updateSellerPersonalInformation(@RequestBody Seller seller) {
        farmMarketService.updateSellerPersonalInformation(seller.getId(), seller.getPersonalInformation());
    }

    @CrossOrigin
    @PutMapping("updateSellerAddress")
    public void updateSellerAddress(@RequestBody Seller seller) {
        farmMarketService.updateSellerAddress(seller.getId(), seller.getAddress());
    }

    @CrossOrigin
    @PutMapping("updateSellerPhone")
    public void updateSellerPhone(@RequestBody Seller seller) {
        farmMarketService.updateSellerPhone(seller.getId(), seller.getPhone());
    }

    @CrossOrigin
    @PutMapping("updateSellerPassword")
    public PopUpWindow updateSellerPassword(@RequestBody Seller seller) {
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

    @CrossOrigin
    @PostMapping("login")
    public String login(@RequestBody Seller seller) {
        if (loginService.validate(seller.getUsername(), seller.getPassword())) {
            Date date = new Date();
            Date expiry = new Date(date.getTime() + 1000*60*60*24);
            JwtBuilder builder = Jwts.builder()
                    .setExpiration(expiry)
                    .setIssuedAt(new Date())
                    .setIssuer("issuer")
                    .signWith(SignatureAlgorithm.HS256,
                            "secret")
                    .claim("usenrame", seller.getUsername());
            String jwt = builder.compact();
            return jwt;
        }
        throw new ApplicationException("vale kasutajanimi või parool");
    }
    @CrossOrigin
    @PutMapping("updateProduct")
    public PopUpWindow updateProduct(@RequestBody Product product){
        farmMarketService.updateProduct(product.getId(), product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("Thank you. Product information is updated.");
    }

}
