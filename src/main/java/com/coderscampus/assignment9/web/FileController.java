package com.coderscampus.assignment9.web;


import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("")
    public String welcomePage () {
        return "Welcome to the Great Recipe Starting Page";
    }

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFreeRecipes () throws IOException {
        return fileService.getAllRecipes().stream()
                .filter(Recipe::getGlutenFree)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes () throws IOException {
        return fileService.getAllRecipes().stream()
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getVeganAndGlutenFreeRecipes () throws IOException {
        return fileService.getAllRecipes().stream()
                .filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
                .collect(Collectors.toList());
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianRecipes () throws IOException {
        return fileService.getAllRecipes().stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
    }

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes () throws IOException {
        return fileService.getAllRecipes();
    }
}
