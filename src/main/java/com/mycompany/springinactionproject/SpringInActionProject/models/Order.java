package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Date placedAt;

//    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

//    @ManyToOne
    @Field("customer")
    private User user;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Zip code is required")
    private String zip;
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }
}
