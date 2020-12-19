package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.exceptions.NotFoundException;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.Recipe;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id_recipe}/show")
    public String showById(@PathVariable String id_recipe, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id_recipe)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id_recipe}/update")
    public String updateRecipe(@PathVariable String id_recipe, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id_recipe)));
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id_recipe}/delete")
    public String deleteRecipe(@PathVariable String id_recipe) {
        recipeService.deleteById(Long.valueOf(id_recipe));
        return "redirect:";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        Recipe savedRecipe = recipeService.saveRecipe(recipe);
        if(bindingResult.hasErrors()) {

            return RECIPE_RECIPEFORM_URL;
        }

        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception exception, Model model) {
        log.error("Handling not found exception!");
        model.addAttribute("exception", exception);
        return "404error";
    }

}
