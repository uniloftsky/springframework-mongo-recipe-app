package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
