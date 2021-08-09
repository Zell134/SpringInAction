package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("spring-in-action-service")
public interface TacoClient {

    @GetMapping("/ingr")
    List<Ingredient> getIngredients();
}
