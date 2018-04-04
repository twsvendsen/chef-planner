package tws.rsi.service;

import java.util.List;

import tws.rsi.model.Recipe;

public interface RecipeService
{
	
	Recipe save(Recipe recipe);
	
	List<Recipe> findAllRecipes();

}
