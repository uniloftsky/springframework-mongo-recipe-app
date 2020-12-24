package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Document
public class Ingredient {

    private String id = UUID.randomUUID().toString();

    private String description;
    private BigDecimal amount;

    @DBRef
    private UnitOfMeasure uof;

    private String recipeId;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uof) {
        this.description = description;
        this.amount = amount;
        this.uof = uof;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uof, String recipeId) {
        this.description = description;
        this.amount = amount;
        this.uof = uof;
        this.recipeId = recipeId;
    }
}
