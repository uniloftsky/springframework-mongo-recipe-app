package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;

import java.util.Set;

public interface UomService {

    Set<UnitOfMeasure> getUoms();

}
