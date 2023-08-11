package com.study.compicafe.menu.repository;

import com.study.compicafe.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(int categoryCode);


}
