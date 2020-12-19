package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    private Long id;

    private Recipe recipe;

    private String recipeNotes;
}
