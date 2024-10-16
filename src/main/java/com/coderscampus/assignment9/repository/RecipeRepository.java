package com.coderscampus.assignment9.repository;

import com.coderscampus.assignment9.domain.Recipe;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeRepository {

    List<Recipe> recipes = new ArrayList<>();

    @PostConstruct
    public void loadRecipes() throws IOException {
        try (FileReader fileReader = new FileReader("src/main/resources/recipes.txt")) {
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader()
                    .withIgnoreSurroundingSpaces()
                    .withEscape('\\')
                    .withQuoteMode(QuoteMode.MINIMAL)
                    .parse(fileReader);

            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord record : records) {
                Recipe recipe = getRecipe(record);
                recipes.add(recipe);
            }
        }
    }

    public List<Recipe> getAll () {
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
}