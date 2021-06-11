package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource(collectionResourceRel = "design", path = "taco")
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>{

}
