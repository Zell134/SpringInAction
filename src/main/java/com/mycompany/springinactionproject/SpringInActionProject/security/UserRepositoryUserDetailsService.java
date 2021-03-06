package com.mycompany.springinactionproject.SpringInActionProject.security;

import com.mycompany.springinactionproject.SpringInActionProject.data.UserRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    UserRepository userRepo;
    
        @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).block();
        if(user!=null)
            return user;
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }


}
