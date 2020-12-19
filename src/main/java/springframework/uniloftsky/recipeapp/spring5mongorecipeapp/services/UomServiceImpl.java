package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import org.springframework.stereotype.Service;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;

    public UomServiceImpl(UomRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    @Override
    public Set<UnitOfMeasure> getUoms() {
        Set<UnitOfMeasure> uoms = new HashSet<>();
        uomRepository.findAll().iterator().forEachRemaining(uoms::add);
        return uoms;
    }
}
