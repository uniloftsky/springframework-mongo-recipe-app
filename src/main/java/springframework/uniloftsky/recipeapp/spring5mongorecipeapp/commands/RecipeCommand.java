package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Category;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Difficulty;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Notes;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Set<Category> categories = new HashSet<>();
    private Notes notes;

}
