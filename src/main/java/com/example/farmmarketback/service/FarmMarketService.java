package com.example.farmmarketback.service;

import com.example.farmmarketback.Responses.CategoriesGetAll;
import com.example.farmmarketback.Responses.ProductGetFullInfo;
import com.example.farmmarketback.exception.ApplicationException;
import com.example.farmmarketback.objects.Category;
import com.example.farmmarketback.objects.Login;
import com.example.farmmarketback.objects.Product;
import com.example.farmmarketback.objects.Seller;
import com.example.farmmarketback.repository.CategoryRepository;
import com.example.farmmarketback.objects.SellerResponse;
import com.example.farmmarketback.repository.FarmMarketRepository;
import com.example.farmmarketback.repository.ProductRepository;
import com.example.farmmarketback.repository.SellerRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FarmMarketService {
    @Autowired
    private FarmMarketRepository farmMarketRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

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

    public void updateSeller(int id, String name, String email, String address, String personalInformation, String phone){
        farmMarketRepository.updateSeller(id, name, email, address, personalInformation, phone);
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

    @Transactional(readOnly = true)
    public Seller getSellerById(Integer id) {
        Optional<Seller> sellerOp = sellerRepository.findById(id);
        Seller seller = sellerOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        sellerRepository.niceShortNameForFunction("midagi");
        return seller;
    }
    @Transactional(readOnly = true)
    public Product getProductById (Integer id) {
        Optional<Product> productOp = productRepository.findById(id);
        Product product = productOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        productRepository.findAllByProductName("midagi");
        return product;
    }

    @Transactional(readOnly = true)
    public Category getCategoryById (Integer id) {
        Optional<Category> categoryOp = categoryRepository.findById(id);
        Category category = categoryOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        categoryRepository.findAllByCategoryName("midagi");
        return category;
    }

    public List<ProductGetFullInfo> findAllProducts() {
        int i = productRepository.findAll().size();
        Product product = new Product();
        List<ProductGetFullInfo> allProducts = new ArrayList<>();
        for(int j=1; j<=i; j++) {
            product = getProductById(j);
            allProducts.add(new ProductGetFullInfo(product));
        }
        return  allProducts;
    }

    public List<CategoriesGetAll> findAllCategories() {
        int i = categoryRepository.findAll().size();
        Category category = new Category();
        List<CategoriesGetAll> allCategories = new ArrayList<>();
        for(int j=1; j<=i; j++) {
            category = getCategoryById(j);
            allCategories.add(new CategoriesGetAll(category));
        }
        return  allCategories;

    }

}
