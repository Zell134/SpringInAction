package com.mycompany.springinactionproject.SpringInActionProject.models;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

public class IngredientModel extends RepresentationModel<IngredientModel>{
    @Getter
    private String name;
    @Getter
    private String type;

    public IngredientModel (Ingredient ingredient){
        
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
    
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
    
}
