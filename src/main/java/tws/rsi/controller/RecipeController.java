package tws.rsi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tws.rsi.model.Recipe;
import tws.rsi.service.RecipeService;

@Controller
@SessionAttributes("recipe")
public class RecipeController
{
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value = "addRecipe", method = RequestMethod.GET)
	public String addRecipe(Model model, HttpSession session) {
		//Recipe recipe = (Recipe)session.getAttribute("recipe"); removed because this is NOT about pulling the same recipe to edit over and over
		
		Recipe recipe = new Recipe();
		recipe.setName("");
		recipe.setCookingHours(0);
		recipe.setCookingMinutes(0);
		model.addAttribute("recipe", recipe);
		
		return "addRecipe";
	}
	
	@RequestMapping(value = "addRecipe", method = RequestMethod.POST)
	public String updateGoal(@Valid @ModelAttribute("recipe") Recipe recipe, HttpServletRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
		
		System.out.println("result has errors: " + result.hasErrors());
		
		if(result.hasErrors())
		{
			return "addRecipe";
		}
		else
		{
			recipeService.save(recipe);
		}
		
		request.getSession().setAttribute("recipe", recipe);
//		redirectAttributes.addFlashAttribute("recipe", recipe);
		
		return "redirect:recipeIngredients.html";
		//return "redirect:index.jsp";
	}
	
	@RequestMapping(value="getRecipes", method=RequestMethod.GET)
	public String getRecipes(Model model)
	{
		List<Recipe> recipes = recipeService.findAllRecipes();
		
		model.addAttribute("recipes", recipes);
		
		return "getRecipes";
	}
}
