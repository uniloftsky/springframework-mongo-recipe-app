package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.RecipeReactiveRepository;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<Recipe> saveRecipe(Recipe recipe) {
        return recipeReactiveRepository.save(recipe);
    }

    @Override
    public void deleteById(String id) {
        recipeReactiveRepository.deleteById(id).block();
    }
}
