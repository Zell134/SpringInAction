package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient implements Serializable {

    @Id
    private String id;
    private String name;
    private String type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Ingredient() {
    this.id = null;
    this.name = null;
    this.type = "";
}

public Ingredient(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static List filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type.toString())).collect(Collectors.toList());
    }
}
