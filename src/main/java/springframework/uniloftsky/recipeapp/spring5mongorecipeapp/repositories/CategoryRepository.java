package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}
