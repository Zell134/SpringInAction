
package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestResource(path = "tacos", rel = "tacos")
@Document
public class Taco {

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    
    @NotNull(message="Name is required")
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    
    private Date createdAt;

//    @ManyToMany(targetEntity = Ingredient.class)
    
    private List<Ingredient> ingredients;
}
