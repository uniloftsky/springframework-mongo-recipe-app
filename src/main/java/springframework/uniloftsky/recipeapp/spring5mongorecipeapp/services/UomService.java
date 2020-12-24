package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import reactor.core.publisher.Flux;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;

public interface UomService {

    Flux<UnitOfMeasure> getUoms();

}
