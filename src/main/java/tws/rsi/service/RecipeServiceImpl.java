package tws.rsi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tws.rsi.model.Recipe;
import tws.rsi.repository.RecipeRepository;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService
{
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Override
	@Transactional
	public Recipe save(Recipe recipe)
	{
		return recipeRepository.save(recipe);
	}

	public Recipe findById(Long id)
	{
		return recipeRepository.findOne(id);
	}
	
	@Override
	public List<Recipe> findAllRecipes()
	{
		return recipeRepository.findAllRecipes();
	}
	
	@Override
	public void delete(Long id)
	{
		recipeRepository.delete(id);
	}

}
