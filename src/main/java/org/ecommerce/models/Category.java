package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.util.CategoryType;

@Setter
@Getter
public class Category extends Identity {
    private String name;
    private String description;
    private CategoryType categoryType;

    Category(Long id, String name, String description, CategoryType categoryType) {
        super(id);
        this.name = name;
        this.description = description;
        this.categoryType = categoryType;
    }

}
