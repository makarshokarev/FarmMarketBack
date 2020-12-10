package com.example.farmmarketback.controller;

import com.example.farmmarketback.responses.*;
import com.example.farmmarketback.entity.Category;
import com.example.farmmarketback.entity.Product;
import com.example.farmmarketback.entity.Seller;
import com.example.farmmarketback.objects.*;
import com.example.farmmarketback.security.MyUser;
import com.example.farmmarketback.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.mail.*;

@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @CrossOrigin
    @PostMapping("photos/upload")
    public void uploadPhoto(@RequestParam (value = "photos") MultipartFile file) throws IOException {
         byte [] a = file.getBytes();
         farmMarketService.uploadFile(a);
                // teha tabel 'file; with column 'bytea'
    }

    @CrossOrigin
    @PostMapping("newSeller")
    public PopUpWindow newSeller(@RequestBody SellerRequest seller) {
        String rawPassword = seller.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getAddress(), seller.getUsername(), encodedPassword, seller.getPhone());
        return new PopUpWindow("Thank you for registration.");
    }

    @CrossOrigin
    @GetMapping("getSeller")
    public SellerResponse getSeller(Authentication authentication){
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        return farmMarketService.getSeller(sellerId);
    }

    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct(Authentication authentication, @RequestBody ProductRequest product) {
        try{
        BigDecimal price = new BigDecimal(product.getPrice().replace(",","."));
        BigDecimal amount = new BigDecimal(product.getAmount().replace(",", "."));
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        farmMarketService.newProduct(sellerId, product.getCategoryId(), product.getProductName(), product.getProductDescription(), price, amount);
        return new PopUpWindow("You have added new product: " + product.getProductName());}
        catch (NullPointerException e){
            return new PopUpWindow("You have to register as a seller first!");
        }
    }

    @GetMapping("category")
    @CrossOrigin
    public List<Category> getAccount() {
        return farmMarketService.getCategory();
    }

    @CrossOrigin
    @GetMapping("product")
    public List<Product> getProductBySeller(Authentication authentication) {
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        int sellerId = userDetails.getId();
        return farmMarketService.getProductBySeller(sellerId);
    }

    @CrossOrigin
    @PutMapping("updateSeller")
    public PopUpWindow updateSeller(@RequestBody SellerRequest seller) {
        farmMarketService.updateSeller(seller.getId(), seller.getName(), seller.getEmail(), seller.getAddress(), seller.getPhone(), seller.getPersonalInformation());
        return new PopUpWindow("Your info changed.");
    }

    @CrossOrigin
    @PutMapping("updateSellerPassword")
    public PopUpWindow updateSellerPassword(@RequestBody SellerRequest seller) {
        String rawPassword = seller.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        farmMarketService.updateSellerPassword(seller.getName(), seller.getUsername(), seller.getEmail(), encodedPassword);
        return new PopUpWindow("Your Password is updated");
    }

    @CrossOrigin
    @GetMapping("getLatestProducts")
    public List<ProductGetFullInfo> getLatest() {
        int mituToodetTasgastada = 6;
        return farmMarketService.getLatestProducts(mituToodetTasgastada);
    }

    @CrossOrigin
    @PostMapping("login")
    public String login(@RequestBody Login login) {
        return farmMarketService.login(login);
    }

    @CrossOrigin
    @PutMapping("updateProduct")
    public PopUpWindow updateProduct(Authentication authentication, @RequestBody ProductRequest product){
        BigDecimal price = new BigDecimal(product.getPrice().replace(",","."));
        BigDecimal amount = new BigDecimal(product.getAmount().replace(",", "."));
        farmMarketService.updateProduct(product.getId(), product.getCategoryId(), product.getProductName(), product.getProductDescription(), price, amount);
        return new PopUpWindow("Thank you. Product information is updated.");
    }

    @CrossOrigin
    @GetMapping("searchProduct")
    public List <ProductGetFullInfo> searchProductByWord (String searchWord) {
        if(searchWord == null || searchWord.isBlank() ){
            return farmMarketService.findAllProducts();
        }
        return farmMarketService.searchProduct(searchWord);
    }
    @CrossOrigin
    @GetMapping("searchProductByCategory")
    public List <ProductGetFullInfo> searchProductByCategory (String searchWord) {
        return farmMarketService.searchProductByCategory(searchWord);
    }

    @CrossOrigin
    @GetMapping("searchProductByCategoryAndName")
    public List <ProductGetFullInfo> searchProductByCategoryAndName (String searchName, String searchCategory) {
        return farmMarketService.searchProductByCategoryAndName(searchName, searchCategory);
    }

    @CrossOrigin
    @GetMapping("sellerInformationById")
    public SellerGetDetailInfo sellerGetDetailInfo(Integer id){
        Seller seller = farmMarketService.getSellerById(id);
        return new SellerGetDetailInfo(seller);
    }

    @CrossOrigin
    @GetMapping("productInformationById")
    public ProductGetFullInfo productGetFullInfo(Integer id){
        Product product = farmMarketService.getProductById(id);
        return new ProductGetFullInfo(product);
    }

    @CrossOrigin
    @GetMapping("findAllCategories")
    public List<CategoriesGetAll> findAllCategories(){
        return farmMarketService.findAllCategories();
    }

    @CrossOrigin
    @PostMapping("contactSeller")
    public PopUpWindow sendEmail(String  message) throws MessagingException {
        farmMarketService.sendEmailtoSeller(message);
        return new PopUpWindow("You message has been sent");
    }

    @CrossOrigin
    @GetMapping("productsByCategory")
    public List<ProductGetFullInfo> getproductsByCategoryName(String name){
        return farmMarketService.getProductsByCategory(name);
    }

    @CrossOrigin
    @DeleteMapping("removeProduct")
    public PopUpWindow removeProduct(Authentication authentication, int id){
        farmMarketService.removeProduct(id);
        return new PopUpWindow("Product deleted");
    }
}
