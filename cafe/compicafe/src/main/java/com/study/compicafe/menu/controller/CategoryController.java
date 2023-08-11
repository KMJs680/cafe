package com.study.compicafe.menu.controller;
import com.study.compicafe.menu.entity.Category;
import com.study.compicafe.menu.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @GetMapping("{categoryCode}")
    public ResponseEntity<Object>findMenyByCode(@PathVariable int categoryCode){
        Category category = categoryService.findCategoryByCode(categoryCode);
        if(Objects.isNull(category)){
            return ResponseEntity.status(404).body(new String("다시입력"));
        }
        return ResponseEntity.ok().body(category);
    }

    //카테고리 리스트
    @GetMapping("/list")
    public ResponseEntity<List<?>>findCategory(){
        List<Category>categoryList = categoryService.findAllCategory();
        if(categoryList.size() <=0 ){
            List<String>error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(categoryList);
    }

    //카테고리 insert
    @PostMapping("/regist")
    public ResponseEntity<?>regist(Category category){
        System.out.println("category : " + category);
        int result = categoryService.registName(category);
        return ResponseEntity.ok().body("성공");
    }
    @PutMapping("/update")
    public ResponseEntity<?>update(Category category){
        Category findCategory = categoryService.findCategoryByCode(category.getCategoryCode());
        if(Objects.isNull(findCategory)){
            return ResponseEntity.ok().body("데이터가 없다");
        }
        System.out.println("데이터 존재 확인 완료");

        int result = categoryService.updateCategory(findCategory, category);
        System.out.println("호출 완료 : " +  result);

        if(result > 0){
            System.out.println("수정완료 : " + result);
            return ResponseEntity.ok().body("수정이 완료됨");
        }else {
            System.out.println("수정 실패함 : " + result);
            return ResponseEntity.status(404).body("수정에 실패하였다.");
        }
    }
    @DeleteMapping("/{delete}")
    public ResponseEntity<?>delete(@PathVariable int delete){
         categoryService.deleteCategory(delete);
         return ResponseEntity.ok().body("삭제완료");
    }



}
