package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.util.Arrays;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails{
    
    private static final long serialVersionUID = 1L;
   
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String sity;
    private String state;
    private String zip;
    private String phonenumber;

    public User(String username, String password, String fullname, String street, String sity, String state, String zip, String phonenumber) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.street = street;
        this.sity = sity;
        this.state = state;
        this.zip = zip;
        this.phonenumber = phonenumber;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
