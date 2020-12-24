package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.UomReactiveRepository;

@Service
public class UomServiceImpl implements UomService {

    private final UomRepository uomRepository;
    private final UomReactiveRepository uomReactiveRepository;

    public UomServiceImpl(UomRepository uomRepository, UomReactiveRepository uomReactiveRepository) {
        this.uomRepository = uomRepository;
        this.uomReactiveRepository = uomReactiveRepository;
    }

    @Override
    public Flux<UnitOfMeasure> getUoms() {
        return uomReactiveRepository.findAll();
    }
}
