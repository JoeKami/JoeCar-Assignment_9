package com.coderscampus.assignment9.service;

import com.coderscampus.assignment9.domain.Recipe;
import com.coderscampus.assignment9.repository.InMemoryRecipeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class FileService {

    private final InMemoryRecipeRepository inMemoryRecipeRepository;

    public FileService(InMemoryRecipeRepository inMemoryRecipeRepository) {
        this.inMemoryRecipeRepository = inMemoryRecipeRepository;
    }

    public List<Recipe> getAllRecipes() throws IOException {
        return inMemoryRecipeRepository.readRecipes();
    }

    public void saveRecipes(List<Recipe> recipes) {
        inMemoryRecipeRepository.saveAll(recipes);
    }

}
