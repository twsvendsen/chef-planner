package tws.rsi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tws.rsi.model.IngredientsList;
import tws.rsi.model.Recipe;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("ingredientsList")
public class RecipeIngredientsController {
	
	@Autowired
	private RecipeService recipeService; // need to make sure re-using autowired RecipeService does not cause problems
	
	@RequestMapping(value = "recipeIngredients", method = RequestMethod.GET)
	public String getRecipeIngredients(@SessionAttribute("recipe") Recipe recipe, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		//Recipe recipe = (Recipe)session.getAttribute("recipe");
		IngredientsList ingredientsList;
		
		if(recipe == null)
			return "redirect:addRecipe.jsp";
		else if(recipe.getIngredientsList() == null)
		{
			ingredientsList = new IngredientsList();
			recipe.setIngredientsList(ingredientsList);
		}
		else
		{
			ingredientsList = recipe.getIngredientsList();
		}
		
//		model.addAttribute("recipe", recipe);
//		redirectAttributes.addFlashAttribute("recipe", recipe);
		model.addAttribute("ingredientsList", ingredientsList);
		return "recipeIngredients";
	}


	
	
//	@RequestMapping(value = "recipeIngredients", method = RequestMethod.POST)
//	public String redirectAddIngredient(@ModelAttribute("recipe") Recipe recipe, RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute("recipe", recipe);
//		return "redirect:addIngredient.html";
//	}
	/*
	public String updateRecipeIngredients(@Valid @ModelAttribute("ingredientsList") IngredientsList ingredientsList, @ModelAttribute("recipe") Recipe recipe, BindingResult result)
	{
		System.out.println("result has errors: " + result.hasErrors());
		
		if(result.hasErrors())
		{
			return "recipeIngredients";
		}
		else
		{
			recipeService.save(recipe); // need to verify saving cascades properly to save underlying IngredientsList / Ingredient objects
		}
		
		return "redirect:index.jsp";
	}
	*/
}
