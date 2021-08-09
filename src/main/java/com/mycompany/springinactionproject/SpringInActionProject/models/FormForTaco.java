
package com.mycompany.springinactionproject.SpringInActionProject.models;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class FormForTaco {

    
    @NotNull(message="Name is required")
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    
 
    private List<String> ingredients;    
}
