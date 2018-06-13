package tws.rsi.service;

import java.util.List;

import tws.rsi.model.Recipe;

public interface RecipeService
{
	
	Recipe save(Recipe recipe);
	
	Recipe findById(Long id);
	
	void delete(Long id);
	
	List<Recipe> findAllRecipes();

}
