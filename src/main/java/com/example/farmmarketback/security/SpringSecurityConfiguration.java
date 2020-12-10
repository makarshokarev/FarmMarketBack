package com.example.farmmarketback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/","/searchProductByCategoryAndName","/sellerInformationById","/searchProductByCategory","productInformationById","sellerInformationById","/findAllProducts","/findAllCategories", "/searchProduct", "/updateSellerPassword", "/login", "/newSeller", "/newProduct", "/category", "/getAllProducts", "/getAllSellers", "/getAllCategories", "/getLatestProducts", "/product", "/getSeller", "/updateSeller",  "/photos/upload", "/contactSeller", "/productsByCategory", "/productInformationById").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
