package com.example.farmmarketback.service;

import com.example.farmmarketback.responses.CategoriesGetAll;
import com.example.farmmarketback.responses.ProductGetFullInfo;
import com.example.farmmarketback.exception.ApplicationException;
import com.example.farmmarketback.entity.Category;
import com.example.farmmarketback.objects.Login;
import com.example.farmmarketback.entity.Product;
import com.example.farmmarketback.entity.Seller;
import com.example.farmmarketback.repository.CategoryRepository;
import com.example.farmmarketback.repository.FarmMarketRepository;
import com.example.farmmarketback.repository.ProductRepository;
import com.example.farmmarketback.repository.SellerRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.*;

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
/*
    public SellerResponse getSeller(int sellerId){
        return farmMarketRepository.getSeller(sellerId);
    }
*/
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

    public List<Product> searchProduct(String searchWord) {
        return farmMarketRepository.searchProduct(searchWord);
    }

    public Seller getSellerById(Integer id) {
        Optional<Seller> sellerOp = sellerRepository.findById(id);
        Seller seller = sellerOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        sellerRepository.niceShortNameForFunction("midagi");
        return seller;
    }

    public Product getProductById (Integer id) {
        Optional<Product> productOp = productRepository.findById(id);
        Product product = productOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        productRepository.findAllByProductName("productName");
        return product;
    }

    public Category getCategoryById (Integer id) {
        Optional<Category> categoryOp = categoryRepository.findById(id);
        Category category = categoryOp.orElseThrow(() -> new RuntimeException("juhtus viga"));
        categoryRepository.findAllByCategoryName("categoryName");
        return category;
    }
    //TODO
    /*
    public List<ProductGetFullInfo> productsByCategory (Integer categoryId) {
        List<Product> productList = productRepository.findAllByCategory(categoryId);
        List<ProductGetFullInfo> allProducts = new ArrayList<>();
    }*/

    public List<ProductGetFullInfo> findAllProducts() {
        List<Integer> allProductsId = farmMarketRepository.allProductsID();
        List<ProductGetFullInfo> allProducts = new ArrayList<>();
        for(int i = allProductsId.size()-1; i>0; i--){
            Product product = getProductById(allProductsId.get(i));
            allProducts.add(new ProductGetFullInfo(product));
        }
        return  allProducts;
    }

    public List<ProductGetFullInfo> getLatestProducts(int number){
        List<Integer> allProductsId = farmMarketRepository.allProductsID();
        List<ProductGetFullInfo> lastProducts = new ArrayList<>();
        for(int i = allProductsId.size()-1; i>0; i--){
            while (number>0){
                Product product = getProductById(allProductsId.get(i));
                lastProducts.add(new ProductGetFullInfo(product));
                number--;}
        }
        return lastProducts;
    }

    public List<CategoriesGetAll> findAllCategories() {
        int i = categoryRepository.findAll().size();
        List<CategoriesGetAll> allCategories = new ArrayList<>();
        for(int j=1; j<=i; j++) {
            Category category = getCategoryById(j);
            allCategories.add(new CategoriesGetAll(category));
        }
        return  allCategories;

    }
    public void uploadFile(byte [] file){
        farmMarketRepository.uploadFile(file);
    }

    public void sendEmailtoSeller()  throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("farmmarketAMI", "FarmMarket2020");
            } });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("farmMarketAMI@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("anna.lazarenkova@gmail.com"));
        message.setSubject("Test email");
        message.setText("Vali IT test");
        Transport.send(message);

    }
}
