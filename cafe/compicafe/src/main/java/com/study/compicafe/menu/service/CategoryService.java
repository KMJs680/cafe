package com.study.compicafe.menu.service;

import com.study.compicafe.menu.entity.Category;
import com.study.compicafe.menu.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category findCategoryByCode(int categoryCode){
        Category category = categoryRepository.findById(categoryCode);
        return category;
    }
    public List<Category> findAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
    @Transactional
    public int registName(Category category) {
        Category result = categoryRepository.save(category);
        System.out.println(result);

        if(Objects.isNull(result)){
            return 0;

        }else {
            return 1;
        }


    }

    @Transactional
    public int updateCategory(Category findCategory, Category category) {
        System.out.println("카테고리 서비스");
        if(!Objects.isNull(category.getCategoryName())) {
            findCategory.setCategoryCode(category.getCategoryCode());
            System.out.println("categoryCode : " + findCategory.getCategoryCode());
            findCategory.setCategoryName(category.getCategoryName());
            System.out.println("categoryName : " + findCategory.getCategoryName());
            findCategory.setRefCategoryCode(category.getRefCategoryCode());
            System.out.println("refCategory : " + findCategory.getRefCategoryCode());
        }
        Category result = categoryRepository.save(findCategory);
        System.out.println("확인 :  " + result);
        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
    @Transactional
    public void deleteCategory(int del){
        categoryRepository.deleteById(del);
        System.out.println("확인 : " + categoryRepository.findById(del));
        Category category = categoryRepository.findById(del);
        System.out.println(category);

    }
}

