package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
    private String url;
    private String directions;
    private Difficulty difficulty;
    private List<Ingredient> ingredients = new ArrayList<>();
    private Byte[] image;
    private List<Category> categories = new ArrayList<>();
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
