package com.mycompany.springinactionproject.SpringInActionProject.models;

import com.mycompany.springinactionproject.SpringInActionProject.api.DesignTacoRestController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel>{

    public TacoModelAssembler() {
        super(DesignTacoRestController.class, TacoModel.class);
    }

    @Override
    protected TacoModel instantiateModel(Taco taco) {
        return new TacoModel(taco);
    }
    
    @Override
    public TacoModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
    
}
