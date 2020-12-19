package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(String id);
    Recipe saveRecipe(Recipe recipe);
    void deleteById(String id);
}
