package com.example.farmmarketback.service;

import com.example.farmmarketback.exception.ApplicationException;
import com.example.farmmarketback.objects.Category;
import com.example.farmmarketback.objects.Login;
import com.example.farmmarketback.objects.Product;
import com.example.farmmarketback.objects.SellerResponse;
import com.example.farmmarketback.repository.FarmMarketRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FarmMarketService {
    @Autowired
    private FarmMarketRepository farmMarketRepository;
    @Autowired
    private LoginService loginService;

    public void newSeller(String name, String email,String username, String password, String phone) {
        if(farmMarketRepository.doesEmailExist(email)){
            throw new ApplicationException("This email is already in use");
        }
        if (farmMarketRepository.doesUsernameExist(username)){
            throw new ApplicationException("This username is already in use");
        }
        try{
        farmMarketRepository.newSeller(name, email, username, password, phone);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public String login(Login login){
        int id = farmMarketRepository.getSellerId(login.getUsername());
        if (loginService.validate(login.getUsername(), login.getPassword())) {
            Date date = new Date();
            Date expiry = new Date(date.getTime() + 1000*60*60*24);
            JwtBuilder builder = Jwts.builder()
                    .setExpiration(expiry)
                    .setIssuedAt(new Date())
                    .setIssuer("issuer")
                    .signWith(SignatureAlgorithm.HS256,
                            "secret")
                    .claim("username", login.getUsername())
                    .claim("id", id);
            return builder.compact();
        }
        throw new ApplicationException("vale kasutajanimi või parool");
    }

    public List<Product> getProductBySeller(int sellerId){
        return farmMarketRepository.getProductBySeller(sellerId);
    }

    public SellerResponse getSeller(){
        return farmMarketRepository.getSeller();
    }

    public void newProduct(int sellerId, int categoryId, String productName, String productDescription,
                           BigDecimal price, BigDecimal amount) {
        farmMarketRepository.newProduct(sellerId, categoryId, productName, productDescription, price, amount);
    }

    public List<Category> getCategory(){
        return farmMarketRepository.getCategory();
    }

    public void updateProduct(int id, int categoryId, String productName, String productDescription,
                              BigDecimal price, BigDecimal amount){
        farmMarketRepository.updateProduct(id, categoryId, productName, productDescription, price, amount);
    }

    public void updateSellerName(int id, String newName){
        farmMarketRepository.updateSellerName(id, newName);
    }

    public void updateSellerUsername(int id, String username){
        farmMarketRepository.updateSellerUsername(id, username);
    }

    public void updateSellerPersonalInformation(int id, String personalInformation){
        farmMarketRepository.updateSellerPersonalInformation(id, personalInformation);
    }

    public void updateSellerAddress(int id, String address){
        farmMarketRepository.updateSellerAddress(id, address);
    }
    public void updateSellerPhone(int id, String phone){
        farmMarketRepository.updateSellerPhone(id, phone);
    }

    public void updateSellerEmail(int id, String email){
        farmMarketRepository.updateSellerEmail(id, email);
    }
    public void updateSellerPassword(String name, String username, String email, String password){
        if(!farmMarketRepository.doesEmailExist(email)){
            throw new ApplicationException("Email is incorrect");}
        String nameInTable = farmMarketRepository.getName(email);
        if(!nameInTable.equals(name)){
            throw new ApplicationException("Name is incorrect");
            }
        String usernameInTable = farmMarketRepository.getUsername(email);
        if(!usernameInTable.equals(username)){
            throw new ApplicationException("Username is incorrect");
            }
        farmMarketRepository.updateSellerPassword(email, password);
    }

    public List<Product> getLatest(){
        List<Integer> numbrid = farmMarketRepository.last3ProductsID();
        List<Product> lastProducts = new ArrayList<>();
        for (Integer integer : numbrid) {
            lastProducts.add(farmMarketRepository.getLatest(integer));
        }
        return lastProducts;
    }

    public List<Product> searchProduct(String searchWord) {
        return farmMarketRepository.searchProduct(searchWord);
    }

}
