package tws.rsi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tws.rsi.model.Ingredient;
import tws.rsi.model.IngredientsList;
import tws.rsi.model.Recipe;
import tws.rsi.service.IngredientsListService;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("recipesList")
public class RecipesListController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientsListService ingredientsListService;
	
	List<Recipe> recipesList;
	
	@RequestMapping(value="recipesList", method=RequestMethod.GET)
	public String getRecipesList(Model model, HttpSession session) {
		
		recipesList = recipeService.findAllRecipes();
		model.addAttribute("recipesList", recipesList);
		
		Integer recipeChoice = new Integer(-1);
		model.addAttribute("recipeChoice", recipeChoice);
		
		return "recipesList";
	}
	
	@RequestMapping(params = {"viewIngredients", "recipeId"}, value = "recipesList", method = RequestMethod.POST)
	public String editRecipeIngredients(@RequestParam(value="recipeId") String recipeId) {
		
		return "redirect:/" + recipeId + "/recipeIngredients.html";
	}
	
	@RequestMapping(params = "newRecipe", value = "recipesList", method = RequestMethod.POST)
	public String newRecipe() {
		
		return "redirect:/addRecipe.html";
	}
	
	@RequestMapping(params = {"editRecipe", "recipeId"}, value = "recipesList", method = RequestMethod.POST)
	public String editRecipe(@RequestParam(value="recipeId") String recipeId) {
		
		return "redirect:/" + recipeId + "/editRecipe.html";
	}
	
	@RequestMapping(params = {"copyRecipe", "recipeId"}, value = "recipesList", method = RequestMethod.POST)
	public String copyRecipe(@RequestParam(value="recipeId") String recipeId) {
		Recipe recipeCopy = new Recipe();
		Recipe originalRecipe = recipeService.findById(Long.parseLong(recipeId));
		recipeCopy.setName(originalRecipe.getName() + " COPY");
		recipeCopy.setCookingHours(originalRecipe.getCookingHours());
		recipeCopy.setCookingMinutes(originalRecipe.getCookingMinutes());
		
		recipeCopy.setIngredientsList(new IngredientsList());
		
		ArrayList<Ingredient> originalIngredients = (ArrayList<Ingredient>)(originalRecipe.getIngredientsList().getIngredients());
		
		for(Ingredient ingredient : originalIngredients) {
			Ingredient ingredientCopy = new Ingredient();
			ingredientCopy.setName(ingredient.getName());
			ingredientCopy.setDescription(ingredient.getDescription());
			ingredientCopy.setMeasurement(ingredient.getMeasurement());
			ingredientCopy.setMeasurementUnit(ingredient.getMeasurementUnit());
			
			recipeCopy.getIngredientsList().addIngredient(ingredientCopy);
			
//			ingredientsListService.save(recipeCopy.getIngredientsList());
			recipeCopy = recipeService.save(recipeCopy);
		}
		
		return "redirect:/recipesList.html";
	}
	
	@RequestMapping(params = {"deleteRecipe", "recipeId"}, value = "recipesList", method = RequestMethod.POST)
	public String deleteRecipe(@RequestParam(value="recipeId") String recipeId) {
		
		recipeService.delete(Long.parseLong(recipeId));
		return "redirect:/recipesList.html";
	}
	
}
