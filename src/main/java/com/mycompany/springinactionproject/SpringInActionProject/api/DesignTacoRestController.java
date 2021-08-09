package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "/tacos", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

    private TacoRepository tacoRepo;

    public DesignTacoRestController(@Qualifier("tacoRepository")TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public Flux<Taco> recentTacos() {
        return tacoRepo.findAll().take(12);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTaco(@PathVariable("id") String id){
        tacoRepo.delete(tacoRepo.findById(id).block());
    }
    
    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") String id) {
            return tacoRepo.findById(id);
    }

}
