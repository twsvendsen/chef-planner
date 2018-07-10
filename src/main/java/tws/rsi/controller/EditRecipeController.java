package tws.rsi.controller;

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

import tws.rsi.model.Recipe;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("recipe")
public class EditRecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "/{recipeId}/editRecipe", method = RequestMethod.GET)
	public String editRecipe(@PathVariable(value="recipeId") Long recipeId, Model model) {
		
		Recipe recipe = recipeService.findById(recipeId);
		model.addAttribute("recipe", recipe);
		return "editRecipe";
	}
	
	@RequestMapping(value = "/{recipeId}/editRecipe", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result) {
		
		System.out.println("result has errors: " + result.hasErrors());
		
		if(result.hasErrors())
			return "addRecipe";
		else
			recipeService.save(recipe);
		
		return "redirect:/recipesList.html";
	}

}
