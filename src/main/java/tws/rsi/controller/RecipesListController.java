package tws.rsi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tws.rsi.model.Recipe;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("recipesList")
public class RecipesListController {

	@Autowired
	private RecipeService recipeService;
	
	List<Recipe> recipesList;
	
	@RequestMapping(value="recipesList", method=RequestMethod.GET)
	public String getRecipesList(Model model, HttpSession session) {
		
		recipesList = recipeService.findAllRecipes();
		model.addAttribute("recipesList", recipesList);
		
		Integer recipeChoice = new Integer(-1);
		model.addAttribute("recipeChoice", recipeChoice);
		
		return "recipesList";
	}
	
	@RequestMapping(params = "recipeChoice", value = "recipesList", method = RequestMethod.POST)
	public String editRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam(value="recipeChoice") String recipeChoice) {
		
		return "redirect:/" + recipeChoice + "/recipeIngredients.html";
	}
	
	@RequestMapping(params = "newRecipe", value = "recipesList", method = RequestMethod.POST)
	public String newRecipe() {
		
		return "redirect:/addRecipe.html";
	}
	
}
