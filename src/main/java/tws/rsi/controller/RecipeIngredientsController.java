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
import tws.rsi.model.Recipe;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("recipe")
public class RecipeIngredientsController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "/{id}/recipeIngredients", method = RequestMethod.GET)
	public String getRecipeIngredients(@SessionAttribute("recipe") Recipe sessionRecipe, @PathVariable(value="id") Long id, Model model) {
		
		Recipe recipe = recipeService.findById(id);
		
		if(recipe == null)
			return "redirect:/addRecipe.html";
//		else
//		{
//			ingredientsList = recipe.getIngredientsList();
//		}
		
		model.addAttribute("recipe", recipe);
//		model.addAttribute("ingredientsList", ingredientsList);
		return "recipeIngredients";
	}
	
	@RequestMapping(params="addIngredient", value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String addIngredient(@RequestParam(value="addIngredient") String addIngredient, @PathVariable(value="id") Long recipeId) {
		
		return "redirect:/" + recipeId.toString() + "/addIngredient.html";
	}
	
	@RequestMapping(params={"editIngredient"}, value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String editIngredient(@RequestParam(value="editIngredient") String ingredientId, @PathVariable(value="id") Long recipeId) {
		
		Recipe recipe = recipeService.findById(recipeId);
		Ingredient ingredient = recipe.findIngredient(Long.parseLong(ingredientId));
		if(ingredient == null)
			return "recipeIngredients";
		else
			return "redirect:/" + recipeId.toString() + "/" + ingredient.getId().toString() + "/editIngredient.html";
	}
	
	@RequestMapping(params={"deleteIngredient"}, value = "{id}/recipeIngredients", method = RequestMethod.POST)
	public String deleteIngredient( @RequestParam(value="deleteIngredient") String ingredientId, @PathVariable(value="id") Long recipeId) {
		
		Recipe recipe = recipeService.findById(recipeId);
		recipe.removeIngredient(Long.parseLong(ingredientId));
		recipeService.save(recipe);
		return "redirect:/" + recipeId.toString() + "/recipeIngredients.html";
	}
}
