package tws.rsi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tws.rsi.model.Ingredient;
import tws.rsi.model.IngredientsList;
import tws.rsi.model.Recipe;
import tws.rsi.service.IngredientService;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("ingredient")
public class AddIngredientController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/{recipeId}/addIngredient", method = RequestMethod.GET)
	public String addIngredient(@PathVariable(value="recipeId") Long recipeId, Model model, HttpSession session) {
		
		Recipe recipe = recipeService.findById(recipeId);
		if(recipe == null)
			return "redirect:addRecipe.html";
		else if(recipe.getIngredientsList() == null)
			return "redirect:/" + recipe.getId().toString() + "/recipeIngredients.html";
		Ingredient ingredient = new Ingredient();
		List<String> measurementUnitOptions = new ArrayList<String>();
		measurementUnitOptions = createMeasurementUnitOptionsList();
		
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("measurementUnitOptions", measurementUnitOptions);
		return "addIngredient";
	}
	
	private static List<String> createMeasurementUnitOptionsList() {
		List<String> returnList = new ArrayList<String>();
		returnList.add("none");
		returnList.add("fluid ounce (fl oz)");
		returnList.add("teaspoon (tsp)");
		returnList.add("cup (c)");
		returnList.add("pint (pt)");
		returnList.add("quart (qt)");
		returnList.add("gallon (gal)");
		returnList.add("ounce (oz)");
		returnList.add("pound (lb)");
		
		return returnList;
	}
	
	@RequestMapping(value = "/{recipeId}/addIngredient", method = RequestMethod.POST)
	public String updateIngredient(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult result,
			@PathVariable(value="recipeId") Long recipeId)
	{
		if(result.hasErrors())
		{
			System.out.println("result has errors: " + result.hasErrors());
			return "addIngredient";
		}
		
		Recipe recipe = recipeService.findById(recipeId);
		
		if(recipe.getIngredientsList() != null)
		{
			IngredientsList ingredientsList = recipe.getIngredientsList();
			
			ingredientsList.addIngredient(ingredient);
			ingredientService.save(ingredient);
//			ingredient.setIngredientsList(ingredientsList);  this is done by ingredientsList.addIngredient()
			
			recipeService.save(recipe);
		}
		else
			System.out.println("Ingredients list is null.");
		
		return "redirect:/" + recipe.getId().toString() + "/recipeIngredients.html";
	}
	
	
}
