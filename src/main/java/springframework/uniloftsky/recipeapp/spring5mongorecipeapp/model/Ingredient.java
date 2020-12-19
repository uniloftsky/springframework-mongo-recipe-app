package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
public class Ingredient {

    @Id
    private String id;

    private String description;
    private BigDecimal amount;

    @DBRef
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
