package com.mycompany.springinactionproject.SpringInActionProject.api;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepo;

    @Autowired
    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }
    
    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").ascending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        
        CollectionModel<TacoModel> recentResources = new TacoModelAssembler().toCollectionModel(tacos);
        recentResources.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }    
}
