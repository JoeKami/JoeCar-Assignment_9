package com.coderscampus.assignment9.service;

import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() throws IOException {
        return recipeRepository.getAll();
    }
}
