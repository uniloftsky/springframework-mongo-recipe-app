package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document
public class Recipe {

    @Id
    private String id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;
    private String source;

    //@URL
    private String url;

    private String directions;

    private Difficulty difficulty;

    private Set<Ingredient> ingredients = new HashSet<>();

    private Byte[] image;

    @DBRef
    private Set<Category> categories = new HashSet<>();

    private Notes notes;

    public void setNotes(Notes notes) {
//        notes.setRecipe(this);
        this.notes = notes;
    }

    public void addIngredient(Ingredient ingredient) {
//        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
    }
}
