package com.odev.yemektarifiodevi.model;

import com.odev.yemektarifiodevi.model.food.Category;
import lombok.Data;

import java.util.List;

@Data
public class Search {

    private String value;
    private List<Category> categories;

}
