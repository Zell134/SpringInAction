package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.TacoClient;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignRestController {
    
    private TacoClient tacoClient;

    @Autowired
    public FeignRestController(TacoClient tacoClient) {
        this.tacoClient = tacoClient;
    }
    
    @GetMapping("/recentIngr")
    public List<Ingredient> recentTacos(){
        return tacoClient.getIngredients();
    }
    
}
