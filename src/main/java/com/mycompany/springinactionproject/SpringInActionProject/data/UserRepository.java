package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
public interface UserRepository extends ReactiveMongoRepository<User, String>{
    
    Mono<User> findByUsername(String username);
}
