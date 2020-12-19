package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Recipe {

    private Long id;

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

    private Set<Category> categories = new HashSet<>();

    private Notes notes;

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
    }
}
