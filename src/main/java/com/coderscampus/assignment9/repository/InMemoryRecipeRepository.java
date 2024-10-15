package com.coderscampus.assignment9.repository;

import com.coderscampus.assignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRecipeRepository implements RecipeRepository {

    List<Recipe> recipes = new ArrayList<>();

    private final ResourceLoader resourceLoader;

    @Autowired
    public InMemoryRecipeRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Recipe> readRecipes() throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:recipes.txt");

        if (!resource.exists()) {
            throw new IOException("File not found: recipes.txt");
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withIgnoreSurroundingSpaces().withEscape('\\')
                    .withQuoteMode(QuoteMode.MINIMAL).parse(in);

            for (CSVRecord record : records) {
                Recipe recipe = getRecipe(record);
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private static Recipe getRecipe(CSVRecord record) {
        Recipe recipe = new Recipe();
        recipe.setCookingMinutes(Integer.parseInt(record.get(0)));
        recipe.setDairyFree(Boolean.parseBoolean(record.get(1)));
        recipe.setGlutenFree(Boolean.parseBoolean(record.get(2)));
        recipe.setInstructions(record.get(3));
        recipe.setPreparationMinutes(Double.parseDouble(record.get(4)));
        recipe.setPricePerServing(Double.parseDouble(record.get(5)));
        recipe.setReadyInMinutes(Integer.parseInt(record.get(6)));
        recipe.setServings(Integer.parseInt(record.get(7)));
        recipe.setSpoonacularScore(Double.parseDouble(record.get(8)));
        recipe.setTitle(record.get(9));
        recipe.setVegan(Boolean.parseBoolean(record.get(10)));
        recipe.setVegetarian(Boolean.parseBoolean(record.get(11)));
        return recipe;
    }

    @Override
    public void saveAll(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
    }
}
