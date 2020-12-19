package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;

public interface IngredientSevice {

    Ingredient findByRecipeIdAndId(Long id_recipe, Long id);
    Ingredient saveIngredient(Ingredient ingredient);
    void delete(Ingredient ingredient);

}
