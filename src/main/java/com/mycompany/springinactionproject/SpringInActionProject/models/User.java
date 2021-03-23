package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String sity;
    private String state;
    private String zip;
    private String phonenumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public User() {
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.street = "";
        this.sity = "";
        this.state = "";
        this.zip = "";
        this.phonenumber = "";
    }   
    
    public String getFullname() {
        return fullname;
    }

    public String getStreet() {
        return street;
    }

    public String getSity() {
        return sity;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
        
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    
}
