package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.IngredientRepository;
import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.FormForTaco;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.Type;
import static com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient.filterByType;
import com.mycompany.springinactionproject.SpringInActionProject.models.Order;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/design")
@CrossOrigin(origins = "*")
@SessionAttributes("order")
public class DesignTacoController {

    private TacoRepository tacoRepo;
    private IngredientRepository ingredientRepo;
        
    public DesignTacoController(TacoRepository tacoRepo, 
                                IngredientRepository ingredientRepo) {
        this.tacoRepo = tacoRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public FormForTaco taco() {
        return new FormForTaco();
    }
    
    @GetMapping
    public String getDesignForm(Model model) {
        setIngredient(model);
        model.addAttribute("design", taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@ModelAttribute("design") @Valid FormForTaco design, Errors errors, Model model, @ModelAttribute("order") Order order) {
        if (errors.hasErrors() || design == null) {
            setIngredient(model);
            model.addAttribute("design", design);
            return "design";
        }
        Taco taco = new Taco();
        taco.setName(design.getName());
        taco.setCreatedAt(new Date());
        List<Ingredient> list = new ArrayList<>();
        design.getIngredients().forEach(elem ->{
                                        Mono<Ingredient> mono = ingredientRepo.findById(elem);
                                        list.add(mono.block());
        });
        taco.setIngredients(list);
        tacoRepo.save(taco).block();
        order.addDesign(taco);
        return "redirect:/orders/current";
    }

    private void setIngredient(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo
                .findAll()
                .map(i -> ingredients.add(i)).blockLast();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

}
