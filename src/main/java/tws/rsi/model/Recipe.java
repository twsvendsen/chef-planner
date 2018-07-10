package tws.rsi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="recipes")
@NamedQueries({
	@NamedQuery(name="Recipe.findAllRecipes", query="Select r from Recipe r") // this name links by-name to the same-named method in the RecipeRepository interface
})
public class Recipe {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 360)
	@Column(name="RECIPE_NAME")
	private String name;
	
	@Valid
	@NumberFormat(style = Style.NUMBER)
	@Range(min = 0, max = 120)
	private Integer cookingHours;
	
	@Valid
	@NumberFormat(style = Style.NUMBER)
	@Range(min = 0, max = 59)
	private Integer cookingMinutes;
	
	@Valid
	@Autowired
	@OneToMany(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@MapKeyColumn(name="ingredient_id")
	private Map<Long, Ingredient> ingredientsMap = new HashMap<>();
	
	public void addAllIngredients(List<Ingredient> ingredients) {
		for(Ingredient ingredient : ingredients) {
			this.ingredientsMap.put(ingredient.getId(), ingredient);
		}
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredientsMap.put(ingredient.getId(), ingredient);
		ingredient.setRecipe(this);
	}

	public Ingredient findIngredient(Long id) {
		return this.ingredientsMap.get(id);
	}

	public Integer getCookingHours() {
		return cookingHours;
	}

	public Integer getCookingMinutes() {
		return cookingMinutes;
	}

	public Long getId() {
		return id;
	}

	public List<Ingredient> getIngredients() {
		List<Ingredient> sp = new ArrayList<Ingredient>(this.ingredientsMap.values());
		return sp;
	}

	public Map<Long, Ingredient> getIngredientsMap() {
		return ingredientsMap;
	}

	public String getName() {
		return name;
	}
	
	public void removeIngredient(Long index) {
		this.ingredientsMap.remove(index);
	}
	
	public void setCookingHours(Integer cookingHours) {
		this.cookingHours = cookingHours;
	}

	public void setCookingMinutes(Integer cookingMinutes) {
		this.cookingMinutes = cookingMinutes;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setIngredientsMap(Map<Long, Ingredient> ingredientsMap) {
		this.ingredientsMap = ingredientsMap;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}