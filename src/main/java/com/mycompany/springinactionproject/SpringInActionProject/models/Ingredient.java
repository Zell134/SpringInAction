package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestResource(path = "ingredients", rel = "ingredients")
public class Ingredient implements Serializable {

    @Id
    private String id;
    private String name;
    private String type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public static List filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type.toString())).collect(Collectors.toList());
    }
}
