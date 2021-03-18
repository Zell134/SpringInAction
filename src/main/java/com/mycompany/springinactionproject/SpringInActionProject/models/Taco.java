
package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotNull(message="Name is required")
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    
    private Date createdAt;

    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
    
    @PrePersist
    void createdAt(){        
        this.createdAt = new Date();
    }
    
    public Taco() {
    }

    public Taco(long id, Date createdAt, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.ingredients = ingredients;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
