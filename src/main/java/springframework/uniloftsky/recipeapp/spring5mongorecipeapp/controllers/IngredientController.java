package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.IngredientSevice;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.RecipeService;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.UomService;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientSevice ingredientSevice;
    private final UomService uomService;

    public IngredientController(RecipeService recipeService, IngredientSevice ingredientSevice, UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientSevice = ingredientSevice;
        this.uomService = uomService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String listOfIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String id_recipe, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientSevice.findByRecipeIdAndId(id_recipe, id));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String id_recipe, @PathVariable String id, Model model) {
        model.addAttribute("uoms", uomService.getUoms());
        model.addAttribute("ingredient", ingredientSevice.findByRecipeIdAndId(id_recipe, id));
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/new")
    public String newIngredient(@PathVariable String id_recipe, Model model) {
        Recipe recipe = recipeService.findById(id_recipe);
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.getRecipe().setId(id_recipe);
        ingredient.setUof(new UnitOfMeasure());
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uoms", uomService.getUoms());
        return "recipe/ingredient/ingredientform";
    }

    @RequestMapping("recipe/{id_recipe}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String id_recipe, @PathVariable String id) {
        ingredientSevice.delete(ingredientSevice.findByRecipeIdAndId(id_recipe, id));
        return "redirect:/recipe/{id_recipe}/ingredients";
    }

    @PostMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/")
    public String saveIngredient(@ModelAttribute Ingredient ingredient) {
        Ingredient savedIngredient = ingredientSevice.saveIngredient(ingredient);
        return "redirect:/recipe/" + savedIngredient.getRecipe().getId() + "/ingredient/" + savedIngredient.getId() + "/show";
    }

}
