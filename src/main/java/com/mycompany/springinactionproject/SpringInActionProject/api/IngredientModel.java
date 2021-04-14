package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import lombok.AllArgsConstructor;
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
