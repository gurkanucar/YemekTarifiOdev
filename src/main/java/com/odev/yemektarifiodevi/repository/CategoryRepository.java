package com.odev.yemektarifiodevi.repository;


import com.odev.yemektarifiodevi.model.food.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {




}
