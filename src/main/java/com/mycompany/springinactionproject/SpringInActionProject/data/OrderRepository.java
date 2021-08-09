package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface OrderRepository extends ReactiveMongoRepository<Order, String>{

}
