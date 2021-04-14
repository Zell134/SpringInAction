package com.mycompany.springinactionproject.SpringInActionProject.security;

import com.mycompany.springinactionproject.SpringInActionProject.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,
                passwordEncoder.encode(password),
                fullname,
                street,
                city,
                state,
                zip,
                phone);
    }
}
