package com.mycompany.springinactionproject.SpringInActionProject.controllers;

import com.mycompany.springinactionproject.SpringInActionProject.data.IngredientRepository;
import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.Type;
import static com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.filterByType;
import com.mycompany.springinactionproject.SpringInActionProject.models.Order;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }
    
    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }
    
    @ModelAttribute(name="design")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String getDesignForm(Model model) {
        setIngredient(model);
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@ModelAttribute("design") @Valid Taco design, Errors errors, Model model, @ModelAttribute("order") Order order) {
        if (errors.hasErrors() || !design.equals(null)) {
            setIngredient(model);
            model.addAttribute("design", design);
            return "design";
        }
        Taco saved = designRepo.save(design);
        List list = new ArrayList<String>();
        list.add(saved.getId());
        order.setDesign(list);
        return "redirect:/orders/current";
    }

    private void setIngredient(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }        
    }
}
