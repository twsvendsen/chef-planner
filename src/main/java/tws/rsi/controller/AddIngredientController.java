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
import tws.rsi.service.IngredientsListService;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("ingredient")
public class AddIngredientController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientsListService ingredientsListService;
	
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/{recipeId}/{ingredientId}/addIngredient", method = RequestMethod.GET)
	public String addIngredient(@PathVariable(value="recipeId") Long recipeId, @PathVariable(value="ingredientId") Long ingredientId, Model model, HttpSession session) {
		
		Recipe recipe = recipeService.findById(recipeId);
		if(recipe == null)
			return "redirect:addRecipe.html";
		else if(recipe.getIngredientsList() == null)
			return "redirect:/" + recipe.getId().toString() + "/recipeIngredients.html";
		Ingredient ingredient = ingredientService.findById(ingredientId);
		if(ingredient == null)
			return "redirect:" + recipeId + "/recipeIngredients";
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
	
	@RequestMapping(value = "/{recipeId}/{ingredientId}/addIngredient", method = RequestMethod.POST)
	public String updateIngredient(@Valid @ModelAttribute("ingredient") Ingredient userIngredient, BindingResult result,
			@PathVariable(value="recipeId") Long recipeId, @PathVariable(value="ingredientId") Long ingredientId)
	{
		Recipe recipe = recipeService.findById(recipeId);
		if(result.hasErrors())
		{
			System.out.println("result has errors: " + result.hasErrors());
			return "addIngredient";
		}
		else if(recipe.getIngredientsList()!=null)
		{
//			--------- Responsibility of below added to IngredientsListController, and IngredientsList addIngredient also sets the ingredient's ingredientsList to itself ---------
//			IngredientsList ingredientsList = recipe.getIngredientsList();
//			ingredientsList.addIngredient(ingredient);
//			ingredient.setIngredientsList(ingredientsList);
			Ingredient ingredient = ingredientService.findById(ingredientId);
			ingredient.setDescription(userIngredient.getDescription());
			ingredient.setMeasurement(userIngredient.getMeasurement());
			ingredient.setMeasurementUnit(userIngredient.getMeasurementUnit());
			ingredient.setName(userIngredient.getName());
			recipeService.save(recipe);
		}
		else
			System.out.println("Ingredients list is null.");
		
		return "redirect:/" + recipe.getId().toString() + "/recipeIngredients.html";
	}
	
	
}
