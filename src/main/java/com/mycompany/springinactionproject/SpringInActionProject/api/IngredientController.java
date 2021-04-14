package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.IngredientRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    
    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return ingredientRepo.findAll();
    }
    
    @GetMapping("{id}")
    public URI getIngredientById(@Autowired RestTemplate rest, @PathVariable("id")String id){     
        
        ResponseEntity<Ingredient> respEntity = rest.getForEntity("http://localhost:8888/api/ingredients/{id}", Ingredient.class, id);
        Ingredient ingredient = respEntity.getBody();
        ingredient.setId(id);
        ingredient.setName("ddd");
        ingredient.setId("ddd");
        return rest.postForLocation("http://localhost:8888/api/ingredients", ingredient, Ingredient.class);
    }
}
