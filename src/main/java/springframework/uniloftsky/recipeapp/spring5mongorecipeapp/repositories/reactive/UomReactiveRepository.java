package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;

public interface UomReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
}
