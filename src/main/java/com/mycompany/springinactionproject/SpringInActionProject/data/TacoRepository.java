package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*")
public interface TacoRepository extends ReactiveMongoRepository<Taco, String>{
    
    Flux<Taco> findByOrderByCreatedAtDesc();

}
