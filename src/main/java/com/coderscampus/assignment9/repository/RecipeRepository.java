package com.coderscampus.assignment9.repository;

import com.coderscampus.assignment9.domain.Recipe;

import java.io.IOException;
import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
    void saveAll(List<Recipe> recipes);
    List<Recipe> readRecipes() throws IOException;
}
