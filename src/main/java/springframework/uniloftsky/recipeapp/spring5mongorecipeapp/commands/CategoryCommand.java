package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();
}
