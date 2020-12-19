package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;

import java.util.Optional;

public interface UomRepository extends CrudRepository<UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByUom(String uom);

}
