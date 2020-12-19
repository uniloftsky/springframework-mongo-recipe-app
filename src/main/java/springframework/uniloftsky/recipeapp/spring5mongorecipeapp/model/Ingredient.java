package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingredient {

    private Long id;

    private String description;
    private BigDecimal amount;

    private UnitOfMeasure uof;

    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uof) {
        this.description = description;
        this.amount = amount;
        this.uof = uof;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uof, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uof = uof;
        this.recipe = recipe;
    }
}
