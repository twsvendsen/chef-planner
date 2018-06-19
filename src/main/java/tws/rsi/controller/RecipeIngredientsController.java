package tws.rsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import tws.rsi.model.Ingredient;
import tws.rsi.model.IngredientsList;
import tws.rsi.model.Recipe;
import tws.rsi.service.IngredientService;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("ingredientsList")
public class RecipeIngredientsController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "/{id}/recipeIngredients", method = RequestMethod.GET)
	public String getRecipeIngredients(@SessionAttribute("recipe") Recipe sessionRecipe, @PathVariable(value="id") Long id, Model model) {
		
		IngredientsList ingredientsList;
		Recipe recipe = recipeService.findById(id);
		
		if(recipe == null)
			return "redirect:/addRecipe.html";
		else if(recipe.getIngredientsList() == null)
		{
			ingredientsList = new IngredientsList();
			recipe.setIngredientsList(ingredientsList);
//			ingredientsList.setRecipe(recipe);  This responsibility has been added to Recipe's setIngredientsList()
			recipeService.save(recipe);
		}
		else
		{
			ingredientsList = recipe.getIngredientsList();
		}
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("ingredientsList", ingredientsList);
		return "recipeIngredients";
	}
	
	@RequestMapping(params="addIngredient", value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String addIngredient(@RequestParam(value="addIngredient") String addIngredient, @PathVariable(value="id") Long recipeId) {
		
		return "redirect:/" + recipeId.toString() + "/addIngredient.html";
	}
	
	@RequestMapping(params="ingredientChoice", value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String editIngredient(@RequestParam(value="ingredientChoice") String ingredientChoice, @PathVariable(value="id") Long recipeId) {
		
		Recipe recipe = recipeService.findById(recipeId);
		Ingredient ingredient = recipe.getIngredientsList().findIngredient(Long.parseLong(ingredientChoice));
		if(ingredient == null)
			return "recipeIngredients";
		else
			return "redirect:/" + recipeId.toString() + "/" + ingredient.getId().toString() + "/editIngredient.html";
	}
	
	@RequestMapping(params="deleteIngredient", value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String deleteIngredient(@RequestParam(value="deleteIngredient") String deleteIngredient, @PathVariable(value="id") Long recipeId) {
		
		Recipe recipe = recipeService.findById(recipeId);
		recipe.getIngredientsList().removeIngredient(Long.parseLong(deleteIngredient));
		recipeService.save(recipe);
		return "redirect:/" + recipeId.toString() + "/recipeIngredients.html";
	}
}
