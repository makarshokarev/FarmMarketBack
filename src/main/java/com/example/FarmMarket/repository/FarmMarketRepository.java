package com.example.FarmMarket.repository;

import com.example.FarmMarket.rowmapper.ProductRowMapper;
import com.example.FarmMarket.objects.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class FarmMarketRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void newSeller(String name, String email, String username, String password, String phone) {
        String sql = "insert into seller(name, email, username, password, phone) values(:m1, :m2, :m3, :m4, :m5)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", name);
        paramMap.put("m2", email);
        paramMap.put("m3", username);
        paramMap.put("m4", password);
        paramMap.put("m5", phone);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getPassword(String username){
        String sql = "Select password from seller where username = :m1";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("m1", username);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public void savePassword(String password){
        String encodedPassword = passwordEncoder.encode(password);
    }


    public boolean doesEmailExist(String email){
        String sql = "SELECT count(*) from seller where email = :m1";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("m1", email);
        Integer count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return count > 0;
    }

    public boolean doesUsernameExist(String username){
        String sql = "SELECT count(*) from seller where username = :m1";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("m1", username);
        Integer count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return count > 0;
    }

    public int getId(int catId) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql1 = "SELECT id FROM category WHERE id = :m1";
        paramMap.put("m1", catId);
        int categoryId = jdbcTemplate.queryForObject(sql1, paramMap, Integer.class);
        return categoryId;
    }


    public void newProduct(int categoryId, String product_name, String product_description,
                           BigDecimal price, BigDecimal amount) {
        String sql = "insert into product (category_id, product_name, product_description, price, amount) values " +
                "(:m1, :m2, :m3, :m4, :m5)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", getId(categoryId));
        paramMap.put("m2", product_name);
        paramMap.put("m3", product_description);
        paramMap.put("m4", price);
        paramMap.put("m5", amount);
        jdbcTemplate.update(sql, paramMap);
    }


    public void updateSellerName(int ID, String newName){
        String sql = "UPDATE seller SET name =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", newName);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);}

    public void updateSellerPersonalInformation(int ID, String personalInformation){
        String sql = "UPDATE seller SET personal_information =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", personalInformation);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerAddress(int ID, String address){
        String sql = "UPDATE seller SET address =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", address);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerEmail(int ID, String email) {
        String sql = "UPDATE seller SET email =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", email);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerPhone(int ID, String phone) {
        String sql = "UPDATE seller SET phone =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", phone);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerUsername(int ID, String username) {
        String sql = "UPDATE seller SET username =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", username);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerPassword(String email, String password) {
        String sql = "UPDATE seller SET password =:m1 WHERE email = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", password);
        paramMap.put("m2", email);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getName( String email) {
        String sql = "SELECT name from seller where email = :m1";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("m1", email);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public String getUsername(String email) {
        String sql = "SELECT username from seller where email = :m1";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("m1", email);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public List<Integer> last3ProductsID(){
        String sql = "SELECT id from product ";
        Map<String, Object> paramMap = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        answer = jdbcTemplate.queryForList(sql, paramMap, Integer.class);
        Collections.sort(answer);
        List<Integer> viimased = new ArrayList<>();
        for(int i =0; i<3; i++){
            viimased.add(answer.get(answer.size()-1-i));
        }
        return viimased;
    }

    public Product getLatest(int number){
        String sql = "SELECT * from product where id = :m1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", number);
        return jdbcTemplate.queryForObject(sql, paramMap, new ProductRowMapper());
    }
}
