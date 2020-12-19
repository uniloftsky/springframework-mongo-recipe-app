package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.*;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.CategoryRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.RecipeRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UomRepository uomRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UomRepository uomRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Data has been loaded");
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        UnitOfMeasure spoon, table, cup, pinch, ounce, dash;

        Optional<UnitOfMeasure> spoonUom = uomRepository.findByUom("Teaspoon");
        Optional<UnitOfMeasure> tableUom = uomRepository.findByUom("Tablespoon");
        Optional<UnitOfMeasure> cupUom = uomRepository.findByUom("Cup");
        Optional<UnitOfMeasure> pinchUom = uomRepository.findByUom("Pinch");
        Optional<UnitOfMeasure> ounceUom = uomRepository.findByUom("Ounce");
        Optional<UnitOfMeasure> dashUom = uomRepository.findByUom("Dash");

        if (spoonUom.isEmpty() || tableUom.isEmpty() || cupUom.isEmpty() || pinchUom.isEmpty() || ounceUom.isEmpty() || dashUom.isEmpty()) {
            System.out.println("Expected UOMs not found!");
        }

        spoon = spoonUom.get();
        table = tableUom.get();
        cup = cupUom.get();
        pinch = pinchUom.get();
        ounce = ounceUom.get();
        dash = dashUom.get();

        Category americanCategory, mexicanCategory;

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (americanCategoryOptional.isEmpty() || mexicanCategoryOptional.isEmpty()) {
            System.out.println("Expected category not found!");
        }

        americanCategory = americanCategoryOptional.get();
        mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setCookTime(1);
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(7);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        guacRecipe.setNotes(guacNotes);
        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(1), ounce));
        guacRecipe.addIngredient(new Ingredient("salt, more to taste", new BigDecimal(".25"), spoon));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), table));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), table));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), ounce));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), table));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), ounce));
        guacRecipe.addIngredient(new Ingredient("red radishes or jicama, to garnish", new BigDecimal(1), ounce));
        guacRecipe.addIngredient(new Ingredient("tortilla chips, to serve", new BigDecimal(1), pinch));
        guacRecipe.setDescription("Guac recipe!");

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setCookTime(15);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setServings(5);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        tacosRecipe.setNotes(tacosNotes);
        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), table));
        tacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), spoon));
        tacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), spoon));
        tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), spoon));
        tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(".5"), spoon));
        tacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), ounce));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), table));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), spoon));
        tacosRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), table));
        tacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(5), ounce));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), ounce));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), ounce));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), ounce));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), ounce));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal("5"), pinch));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), ounce));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), ounce));
        tacosRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(".5"), cup));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), ounce));
        tacosRecipe.setDescription("Tacos recipe!");

        recipes.add(tacosRecipe);

        return recipes;
    }
}
