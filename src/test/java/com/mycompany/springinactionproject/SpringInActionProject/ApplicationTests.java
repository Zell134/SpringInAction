package com.mycompany.springinactionproject.SpringInActionProject;

import com.mycompany.springinactionproject.SpringInActionProject.api.DesignTacoRestController;
import com.mycompany.springinactionproject.SpringInActionProject.data.TacoRepository;
import com.mycompany.springinactionproject.SpringInActionProject.models.Ingredient;
import com.mycompany.springinactionproject.SpringInActionProject.models.Taco;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class ApplicationTests {

    @Test
    public void getTest() throws Exception {

        List<Taco> tacos;
        tacos = Stream.of(
            testTaco(1L), testTaco(2L),
            testTaco(3L), testTaco(4L),
            testTaco(5L), testTaco(6L),
            testTaco(7L), testTaco(8L),
            testTaco(9L), testTaco(10L),
            testTaco(11L), testTaco(12L),
            testTaco(13L), testTaco(14L),
            testTaco(15L), testTaco(16L)).collect(Collectors.toList());
        Flux<Taco> tacoFlux = Flux.fromIterable(tacos);

        TacoRepository repository = Mockito.mock(TacoRepository.class);
        Mockito.when(repository.findAll()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToController(
                new DesignTacoRestController(repository)).build();

        testClient.get().uri("/tacos/recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos.get(0).getId())
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[1].id").isEqualTo(tacos.get(1).getId())
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[11].id").isEqualTo(tacos.get(11).getId())
                .jsonPath("$[11].name").isEqualTo("Taco 12")
                .jsonPath("$[12]").doesNotExist();

    }
    
    @Test
    private void postTest(){
        TacoRepository repository = Mockito.mock(TacoRepository.class);
        
        Mono<Taco> usavedTaco = Mono.just(testTaco(null));
        Taco savedTaco = testTaco(null);
        Mono<Taco> savedTacoMono = Mono.just(savedTaco);
        
        Mockito.when(repository.save(Mockito.any())).thenReturn(savedTacoMono);
        
        WebTestClient testClient = WebTestClient.bindToController(
                new DesignTacoRestController(repository)).build();
        
        testClient
                .post()
                .uri("/tacos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedTaco, Taco.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Mono.class)
                .isEqualTo(usavedTaco);
                
    }

    private Taco testTaco(Long number) {
        Taco taco = new Taco();
        String id = "1";
        taco.setId(id);
        taco.setName("Taco " + number);
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setType("WRAP");
        ingredient.setName("Ingredient A");
        ingredients.add(ingredient);
        ingredient = new Ingredient();
        ingredient.setType("PROTEIN");
        ingredient.setName("Ingredient B");
        ingredients.add(ingredient);
        taco.setIngredients(ingredients);
        return taco;
    }

}
