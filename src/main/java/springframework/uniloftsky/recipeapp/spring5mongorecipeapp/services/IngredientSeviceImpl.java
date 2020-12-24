package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.RecipeRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.RecipeReactiveRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.UomReactiveRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientSeviceImpl implements IngredientSevice {

    private  final RecipeRepository recipeRepository;
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UomReactiveRepository uomReactiveRepository;
    private final UomRepository uomRepository;

    public IngredientSeviceImpl(RecipeReactiveRepository recipeReactiveRepository, UomRepository uomRepository, RecipeRepository recipeRepository, UomReactiveRepository uomReactiveRepository, UomRepository uomRepository1) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeRepository = recipeRepository;
        this.uomReactiveRepository = uomReactiveRepository;
        this.uomRepository = uomRepository1;
    }

    @Override
    public Mono<Ingredient> findByRecipeIdAndId(String id_recipe, String id) {
        return recipeReactiveRepository
                .findById(id_recipe)
                .flatMapIterable(Recipe::getIngredients)
                .filter(ingredient -> ingredient.getId().equals(id))
                .single();
    }

    @Override
    public Mono<Ingredient> saveIngredient(Ingredient ingredient) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredient.getRecipeId());

        if(recipeOptional.isEmpty()) {
            return Mono.just(new Ingredient());
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient1 -> ingredient.getId().equals(ingredient.getId()))
                    .findFirst();
            if(ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setAmount(ingredient.getAmount());
                ingredientFound.setDescription(ingredient.getDescription());
                ingredientFound.setUof(uomReactiveRepository.findById(ingredient.getUof().getId()).block());
                if(ingredientFound.getUof() == null) {
                    throw new RuntimeException("UOM not found!");
                }
            } else {
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredient.getId()))
                    .findFirst();

            if(savedIngredientOptional.isEmpty()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingredient.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingredient.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUof().getId().equals(ingredient.getUof().getId()))
                        .findFirst();
            }

            ingredient.setRecipeId(recipe.getId());
            return Mono.just(ingredient);
        }

    }

    @Override
    public Mono<Void> deleteById(String ingredient_id, String recipe_id) {
        Recipe recipeReactive = recipeReactiveRepository.findById(recipe_id).block();
        Recipe recipeOptional = recipeRepository.findById(recipe_id).get();

        if(recipeOptional == null) {
            throw new RuntimeException("ERROR");
        } else {
            Optional<Ingredient> ingredientOptional = recipeOptional
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredient_id))
                    .findFirst();
            if(ingredientOptional.isPresent()) {
                recipeOptional.getIngredients().remove(ingredientOptional.get());
                recipeReactiveRepository.save(recipeOptional).block();
            } else {
                log.error("Ingredient not found");
            }
        }
        return Mono.empty();
    }
}
