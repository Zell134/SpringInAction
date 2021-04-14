
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestResource(path = "tacos", rel = "tacos")
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
}
