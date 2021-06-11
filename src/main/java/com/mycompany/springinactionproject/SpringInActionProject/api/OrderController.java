package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.OrderRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Order;
import com.mycompany.springinactionproject.SpringInActionProject.models.User;
import java.util.Date;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    
    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
 
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@ModelAttribute @Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if(errors.hasErrors())
            return "orderForm";   
        order.setUser(user);
        order.setPlacedAt(new Date());
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
