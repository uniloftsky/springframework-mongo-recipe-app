package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.RecipeRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientSeviceImpl implements IngredientSevice {

    private final RecipeRepository recipeRepository;
    private final UomRepository uomRepository;

    public IngredientSeviceImpl(RecipeRepository recipeRepository, UomRepository uomRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public Ingredient findByRecipeIdAndId(String id_recipe, String id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id_recipe);
        if(recipeOptional.isEmpty()) {
            throw new RuntimeException("Expected recipe is not found!");
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(id)).findFirst();
        if(ingredientOptional.isEmpty()) {
            throw new RuntimeException("Expected ingredient not found!");
        }

        return ingredientOptional.get();
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredient.getRecipe().getId());

        if(recipeOptional.isEmpty()) {
            throw new RuntimeException("ERROR");
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId()))
                    .findFirst();

            if(ingredientOptional.isEmpty()) {
                recipe.addIngredient(ingredient);
            } else {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredient.getDescription());
                ingredientFound.setUof(ingredient.getUof());
                ingredientFound.setAmount(ingredient.getAmount());
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredient.getId()))
                    .findFirst();

            if(savedIngredientOptional.isEmpty()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingredient.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingredient.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUof().getId().equals(ingredient.getUof().getId()))
                        .findFirst();
            }

            return savedIngredientOptional.get();
        }

    }

    @Override
    public void delete(Ingredient ingredient) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredient.getRecipe().getId());

        if(recipeOptional.isEmpty()) {
            throw new RuntimeException("ERROR");
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId())).findFirst();
            if(ingredientOptional.isEmpty()) {
                throw new RuntimeException("Expected ingredient not found!");
            } else {
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredient);
            }
            recipeRepository.save(recipe);
        }
    }
}
