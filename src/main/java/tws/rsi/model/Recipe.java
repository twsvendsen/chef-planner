package tws.rsi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Component;

@Entity
@Table(name="recipes")
@NamedQueries({
	@NamedQuery(name="Recipe.findAllRecipes", query="Select r from Recipe r") // this name links by-name to the same-named method in the RecipeRepository interface
})
public class Recipe
{
	//public static final String FIND_ALL_RECIPES = "findAllRecipes"; 
	
	@Id
	@GeneratedValue
	//@Column(name="RECIPE_ID")
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
	
	@OneToOne(mappedBy="recipe", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private IngredientsList ingredientsList;
	
	public Integer getCookingHours()
	{
		return cookingHours;
	}

	public Integer getCookingMinutes()
	{
		return cookingMinutes;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setCookingHours(Integer cookingHours)
	{
		this.cookingHours = cookingHours;
	}

	public void setCookingMinutes(Integer cookingMinutes)
	{
		this.cookingMinutes = cookingMinutes;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void addIngredient(Ingredient ingredient)
	{
		this.ingredientsList.addIngredient(ingredient);
	}
	
	public void addAllIngredients(List<Ingredient> ingredients) {
		this.ingredientsList.addAllIngredients(ingredients);
	}

	public IngredientsList getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(IngredientsList ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}