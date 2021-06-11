package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource(collectionResourceRel = "ingredients", path = "ingredients")
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, String>{

    
}
