package tws.rsi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@OneToMany(mappedBy="ingredientsList", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Ingredient> ingredients = new ArrayList<>();

	@OneToOne
	private Recipe recipe;

	public void addAllIngredients(List<Ingredient> ingredients) {
		this.ingredients.addAll(ingredients);
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	// constructor necessary for ingredients instantiation - doable through Autowiring instead?
//	public IngredientsList() {
//		ingredients = new ArrayList<Ingredient>();
//	}
	
	public Long getId() {
		return id;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}

	public void removeIngredient(int index) {
		this.ingredients.remove(index);
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
}
