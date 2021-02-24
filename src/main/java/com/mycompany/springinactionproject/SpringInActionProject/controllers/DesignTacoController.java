package com.mycompany.springinactionproject.SpringInActionProject.controllers;

import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.Type;
import static com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.filterByType;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String getDesignForm(Model model) {
        setIngredientd(model);
        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@ModelAttribute("design") @Valid Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            setIngredientd(model);
            model.addAttribute("design", design);
            return "design";
        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
    
    private void setIngredientd(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                    new Ingredient("FLTO", "Flour Tortilla", Type.WRAP.toString()),
                    new Ingredient("COTO", "Corn Tortilla", Type.WRAP.toString()),
                    new Ingredient("GRBF", "Ground Beef", Type.PROTEIN.toString()),
                    new Ingredient("CARN", "Carnitas", Type.PROTEIN.toString()),
                    new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES.toString()),
                    new Ingredient("LETC", "Lettuce", Type.VEGGIES.toString()),
                    new Ingredient("CHED", "Cheddar", Type.CHEESE.toString()),
                    new Ingredient("JACK", "Monterrey Jack", Type.CHEESE.toString()),
                    new Ingredient("SLSA", "Salsa", Type.SAUCE.toString()),
                    new Ingredient("SRCR", "Sour Cream", Type.SAUCE.toString())
            );
            Type[] types = Ingredient.Type.values();
            for (Type type : types) {
                model.addAttribute(type.toString().toLowerCase(),
                        filterByType(ingredients, type));
            }
    }
}
