package com.coderscampus.assignment9.repository;

import com.coderscampus.assignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRecipeRepository implements RecipeRepository {

    List<Recipe> recipes = new ArrayList<>();

    @Override
    public List<Recipe> findAll() {
        return recipes;
    }

    @Override
    public void saveAll(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);

    }

    @Override
    public List<Recipe> readRecipes () throws IOException {
        List<Recipe> recipes = new ArrayList<>();

        try(Reader in = new FileReader("recipes.txt")){

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().withIgnoreSurroundingSpaces().parse(in);

            for(CSVRecord record : records) {
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
                recipes.add(recipe);
            }

        }
        return recipes;
    }

}
