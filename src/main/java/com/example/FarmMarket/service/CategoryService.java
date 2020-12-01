package com.example.FarmMarket.service;
import com.example.FarmMarket.objects.Category;
import com.example.FarmMarket.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getCategory(){
        List<Category> result = categoryRepo.getCategory();
        return result;
    }
}
