package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.IngredientRepository;
import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import com.mycompany.springinactionproject.SpringInActionProject.models.TacoModel;
import com.mycompany.springinactionproject.SpringInActionProject.models.TacoModelAssembler;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@RepositoryRestController
@RequestMapping(path = "/tacos", produces = "application/hal+json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

    private TacoRepository tacoRepo;
    private IngredientRepository ingredientRepo;
    @Autowired
    EntityLinks entityLinks;

    public DesignTacoRestController(TacoRepository tacoRepo, IngredientRepository ingredientRepo) {
        this.tacoRepo = tacoRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoModel>> recentTaco() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").ascending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        
        CollectionModel<TacoModel> recentResources = new TacoModelAssembler().toCollectionModel(tacos);
        recentResources.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DesignTacoRestController.class).recentTaco()).withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Taco patchTaco(
            @PathVariable("id") Long id,
            @RequestBody Taco tacoToPatch
    ) {
        Taco taco = tacoRepo.findById(id).get();
        if (tacoToPatch.getName() != null) {
            taco.setName(tacoToPatch.getName());
        }
        if (tacoToPatch.getIngredients() != null) {
            taco.setIngredients(tacoToPatch.getIngredients());
        }
        return tacoRepo.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id) {
        try {
            tacoRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
}
