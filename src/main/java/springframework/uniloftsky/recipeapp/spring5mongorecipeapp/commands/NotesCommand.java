package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private Recipe recipe;
    private String recipeNotes;

}
