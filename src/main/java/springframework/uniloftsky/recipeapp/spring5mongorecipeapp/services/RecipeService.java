package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

public interface RecipeService {
    Flux<Recipe> getRecipes();
    Mono<Recipe> findById(String id);
    Mono<Recipe> saveRecipe(Recipe recipe);
    void deleteById(String id);
}
