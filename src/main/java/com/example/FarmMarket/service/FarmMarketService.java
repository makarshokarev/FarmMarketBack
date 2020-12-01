package com.example.FarmMarket.service;

import com.example.FarmMarket.exception.ApplicationException;
import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.repository.FarmMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmMarketService {
    @Autowired
    private FarmMarketRepository farmMarketRepository;

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

    public void newProduct(int categoryId, String product_name, String product_description,
                           BigDecimal price, BigDecimal amount) {
        farmMarketRepository.newProduct(categoryId, product_name, product_description, price, amount);
    }

    public void updateProduct(int id, int categoryId, String product_name, String product_description,
                              BigDecimal price, BigDecimal amount){
        farmMarketRepository.updateProduct(id, categoryId, product_name, product_description, price, amount);
    }

    public void updateSellerName(int ID, String newName){
        farmMarketRepository.updateSellerName(ID, newName);
    }

    public void updateSellerUsername(int ID, String username){
        farmMarketRepository.updateSellerUsername(ID, username);
    }

    public void updateSellerPersonalInformation(int ID, String personalInformation){
        farmMarketRepository.updateSellerPersonalInformation(ID, personalInformation);
    }

    public void updateSellerAddress(int ID, String address){
        farmMarketRepository.updateSellerAddress(ID, address);
    }
    public void updateSellerPhone(int ID, String phone){
        farmMarketRepository.updateSellerPhone(ID, phone);
    }

    public void updateSellerEmail(int ID, String email){
        farmMarketRepository.updateSellerEmail(ID, email);
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

}
