package springframework.uniloftsky.recipeapp.spring5mongorecipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.model.*;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.CategoryRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.RecipeRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.UomRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.CategoryReactiveRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.RecipeReactiveRepository;
import springframework.uniloftsky.recipeapp.spring5mongorecipeapp.repositories.reactive.UomReactiveRepository;

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

    private final UomReactiveRepository uomReactiveRepository;
    private final CategoryReactiveRepository categoryReactiveRepository;
    private final RecipeReactiveRepository recipeReactiveRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UomRepository uomRepository, UomReactiveRepository uomReactiveRepository, CategoryReactiveRepository categoryReactiveRepository, RecipeReactiveRepository recipeReactiveRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.uomReactiveRepository = uomReactiveRepository;
        this.categoryReactiveRepository = categoryReactiveRepository;
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadCategories();
        loadUom();
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");

        log.error("###########");
        log.error(uomReactiveRepository.count().block().toString());
        log.error(categoryReactiveRepository.count().block().toString());
        log.error(recipeReactiveRepository.count().block().toString());

    }

    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setUom("Teaspoon");
        uomRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setUom("Tablespoon");
        uomRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setUom("Cup");
        uomRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setUom("Pinch");
        uomRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setUom("Ounce");
        uomRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setUom("Each");
        uomRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setUom("Pint");
        uomRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setUom("Dash");
        uomRepository.save(uom8);
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = uomRepository.findByUom("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = uomRepository.findByUom("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = uomRepository.findByUom("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = uomRepository.findByUom("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = uomRepository.findByUom("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = uomRepository.findByUom("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        Recipe savedGuacRecipe = recipeRepository.save(guacRecipe);
        Optional<Recipe> guacRecipeOptional = recipeRepository.findById(savedGuacRecipe.getId());
        Recipe foundGuacRecipe = guacRecipeOptional.get();

        //very redundent - could add helper method, and make this simpler
        foundGuacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teapoonUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, foundGuacRecipe.getId()));
        foundGuacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom, foundGuacRecipe.getId()));

        foundGuacRecipe.getCategories().add(americanCategory);
        foundGuacRecipe.getCategories().add(mexicanCategory);

        foundGuacRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        foundGuacRecipe.setServings(4);
        foundGuacRecipe.setSource("Simply Recipes");

        //add to return list
        recipes.add(foundGuacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);

        Recipe savedTacosRecipe = recipeRepository.save(tacosRecipe);
        Optional<Recipe> tacosRecipeOptional = recipeRepository.findById(savedTacosRecipe.getId());
        Recipe foundTacosRecipe = tacosRecipeOptional.get();

        foundTacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teapoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teapoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teapoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, foundTacosRecipe.getId()));
        foundTacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, foundTacosRecipe.getId()));

        foundTacosRecipe.getCategories().add(americanCategory);
        foundTacosRecipe.getCategories().add(mexicanCategory);

        foundTacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        foundTacosRecipe.setServings(4);
        foundTacosRecipe.setSource("Simply Recipes");

        recipes.add(foundTacosRecipe);
        return recipes;
    }
}
