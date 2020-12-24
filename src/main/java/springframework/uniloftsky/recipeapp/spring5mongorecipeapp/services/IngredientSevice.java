package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import reactor.core.publisher.Mono;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;

public interface IngredientSevice {

    Mono<Ingredient> findByRecipeIdAndId(String id_recipe, String id);
    Mono<Ingredient> saveIngredient(Ingredient ingredient);
    Mono<Void> deleteById(String ingredient_id, String id_recipe);

}
