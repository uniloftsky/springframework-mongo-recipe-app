package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Ingredient;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.UnitOfMeasure;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.RecipeReactiveRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.UomReactiveRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.IngredientSevice;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.RecipeService;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.UomService;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientSevice ingredientSevice;
    private final UomService uomService;
    private final UomReactiveRepository uomReactiveRepository;
    private final RecipeReactiveRepository recipeReactiveRepository;

    public IngredientController(RecipeService recipeService, IngredientSevice ingredientSevice, UomService uomService, UomReactiveRepository uomReactiveRepository, RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeService = recipeService;
        this.ingredientSevice = ingredientSevice;
        this.uomService = uomService;
        this.uomReactiveRepository = uomReactiveRepository;
        this.recipeReactiveRepository = recipeReactiveRepository;
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
        model.addAttribute("ingredient", ingredientSevice.findByRecipeIdAndId(id_recipe, id).block());
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String id_recipe, @PathVariable String id, Model model) {
        model.addAttribute("uoms", uomReactiveRepository.findAll().collectList().block());
        model.addAttribute("ingredient", ingredientSevice.findByRecipeIdAndId(id_recipe, id).block());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{id_recipe}/ingredient/new")
    public String newIngredient(@PathVariable String id_recipe, Model model) {
        Recipe recipe = recipeReactiveRepository.findById(id_recipe).block();
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipeId(id_recipe);
        recipe.getIngredients().add(ingredient);
        ingredient.setUof(new UnitOfMeasure());
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uoms", uomReactiveRepository.findAll().collectList().block());
        return "recipe/ingredient/ingredientform";
    }

    @RequestMapping("recipe/{id_recipe}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String id_recipe, @PathVariable String id) {
        ingredientSevice.deleteById(id, id_recipe).block();
        return "redirect:/recipe/{id_recipe}/ingredients";
    }

    @PostMapping("recipe/{id_recipe}/ingredient")
    public String saveIngredient(@ModelAttribute Ingredient ingredient) {
        Ingredient savedIngredient = ingredientSevice.saveIngredient(ingredient).block();
        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId() + "/show";
    }

}
