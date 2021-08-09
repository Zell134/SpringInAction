package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.IngredientRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import com.mycompany.springinactionproject.SpringInActionProject.services.RabbitMessageSender;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ingr", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

    private IngredientRepository ingredientRepo;
    private RabbitMessageSender messageSender;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepo, RabbitMessageSender messageSender) {
        this.ingredientRepo = ingredientRepo;
        this.messageSender = messageSender;
    }

    @GetMapping
    public List<Ingredient> allIngredients() {
        List<Ingredient> ingredients = ingredientRepo.findAll().collectList().block();
        ingredients.forEach(ingr
                -> messageSender.sendMessage(ingr.toString()));
        return ingredients;
    }

    @GetMapping("{id}")
    public Ingredient getIngredient(@PathVariable("id") String id) {
        return ingredientRepo.findById(id).block();
    }

}
