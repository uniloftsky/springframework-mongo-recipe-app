package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure uof;
    private Recipe recipe;

}
