package com.mycompany.springinactionproject.SpringInActionProject.api;

import com.mycompany.springinactionproject.SpringInActionProject.data.UserRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.User;
import com.mycompany.springinactionproject.SpringInActionProject.security.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @GetMapping
    public String registerForm(){
        return "registration";
    }
    
    @PostMapping
    public String ProcessRegistration(RegistrationForm form){
        User user = form.toUser(passwordEncoder);
        user = userRepo.save(user).block();
        return "redirect:/login";
    }
    
}
