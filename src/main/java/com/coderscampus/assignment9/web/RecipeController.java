package com.coderscampus.assignment9.web;


import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Recipe> getGlutenFreeRecipes () throws IOException {
        return recipeService.getAllRecipes().stream()
                .filter(Recipe::getGlutenFree)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes () throws IOException {
        return recipeService.getAllRecipes().stream()
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFreeRecipes () throws IOException {
        return recipeService.getAllRecipes().stream()
                .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
                .collect(Collectors.toList());
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianRecipes () throws IOException {
        return recipeService.getAllRecipes().stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
    }

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes () throws IOException {
        return recipeService.getAllRecipes();
    }
}
