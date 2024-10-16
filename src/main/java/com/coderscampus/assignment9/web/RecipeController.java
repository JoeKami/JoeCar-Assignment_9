package com.coderscampus.assignment9.web;

import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public String welcomePage () {
        return "Welcome to the Great Recipe Starting Page";
    }

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFreeRecipes () {
        return recipeService.getOnlyGlutenFreeRecipes();
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes () {
        return recipeService.getOnlyVeganRecipes();
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFreeRecipes () {
        return recipeService.getOnlyVeganAndGlutenFreeRecipes();
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianRecipes () {
        return recipeService.getOnlyVegetarianRecipes();
    }

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes () {
        return recipeService.getAllRecipes();
    }
}
