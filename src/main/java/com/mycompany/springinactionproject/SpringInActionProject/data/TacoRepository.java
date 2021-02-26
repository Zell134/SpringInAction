package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;

public interface TacoRepository {
    
    Taco save(Taco design);
}
