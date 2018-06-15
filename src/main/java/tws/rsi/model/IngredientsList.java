package tws.rsi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="ingredientsList")
@NamedQueries({
	@NamedQuery(name="IngredientsList.findAllIngredientsLists", query="Select i from IngredientsList i") // this name links by-name to the same-named method in the RecipeRepository interface
})
public class IngredientsList {
	
	@Id
	@GeneratedValue
	//@Column(name="ID")
	private Long id;
	
	@Valid
	@Autowired
	@OneToMany(mappedBy="ingredientsList", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@MapKey(name="id")
	private Map<Long, Ingredient> ingredientsMap = new HashMap<>();

	@OneToOne
	private Recipe recipe;

	public void addAllIngredients(List<Ingredient> ingredients) {
//		this.ingredients.addAll(ingredients);
		for(Ingredient ingredient : ingredients) 
		{
			this.ingredientsMap.put(ingredient.getId(), ingredient);
			ingredient.setIngredientsList(this);
		}
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredientsMap.put(ingredient.getId(), ingredient);
		ingredient.setIngredientsList(this);
	}

	// constructor necessary for ingredients instantiation - doable through Autowiring instead?
//	public IngredientsList() {
//		ingredients = new ArrayList<Ingredient>();
//	}
	
	public Long getId() {
		return id;
	}
	
	public Map<Long, Ingredient> getIngredientsMap() {
		return ingredientsMap;
	}
	
	public List<Ingredient> getIngredients() {
		return new ArrayList<Ingredient>(this.ingredientsMap.values());
	}
	
	public Recipe getRecipe() {
		return recipe;
	}

	public void removeIngredient(Long index) {
		this.ingredientsMap.remove(index);
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIngredientsMap(Map<Long, Ingredient> ingredients) {
		this.ingredientsMap = ingredients;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
