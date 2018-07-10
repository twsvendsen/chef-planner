package tws.rsi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="ingredient")
@NamedQueries({
	@NamedQuery(name="Ingredient.findAllIngredients", query="Select i from Ingredient i") // this name links by-name to the same-named method in the RecipeRepository interface
})
public class Ingredient
{
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="ingredient_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="RECIPE_ID")
	private Recipe recipe;
	
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@NotNull
	@Size(min = 1, max = 360)
	@Column(name="INGREDIENT_NAME")
	private String name;
	
	private String measurementUnit;
	
	@Valid
	@NumberFormat(style = Style.NUMBER)
	private Long measurement;

	@Valid
	@Size(min = 0, max = 500)
	private String description;
	
	public Ingredient() {
		this.name = "";
		this.measurement = new Long(0);
		this.measurementUnit = Long.toString(-1);
		this.description = "";
	}

	public String getDescription() {
		return description;
	}

	public Long getId()
	{
		return id;
	}
	
	public Long getMeasurement() {
		return measurement;
	}
	
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setMeasurement(Long measurement) {
		this.measurement = measurement;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public void setName(String name) {
		this.name = name;
	}
}