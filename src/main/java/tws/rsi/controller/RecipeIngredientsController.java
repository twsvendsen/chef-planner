package tws.rsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import tws.rsi.model.IngredientsList;
import tws.rsi.model.Recipe;
import tws.rsi.service.IngredientsListService;
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
			return "redirect:addRecipe.jsp";
		else if(recipe.getIngredientsList() == null)
		{
			ingredientsList = new IngredientsList();
			recipe.setIngredientsList(ingredientsList);
			ingredientsList.setRecipe(recipe);
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
}
