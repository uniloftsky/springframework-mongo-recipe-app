package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
