package tws.rsi.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	@GeneratedValue
	//@Column(name="INGREDIENT_ID")
	private Long id;
	
	@ManyToOne
	private IngredientsList ingredientsList;
	
	@NotNull
	@Size(min = 1, max = 360)
	@Column(name="INGREDIENT_NAME")
	private String name;
	
	private String measurementUnit;
	
	@Valid
	@NumberFormat(style = Style.NUMBER)
	private Long measurement;

	@Size(min = 0, max = 500)
	private String description;

	public String getDescription() {
		return description;
	}

	public Long getId()
	{
		return id;
	}

	public IngredientsList getIngredientsList() {
		return ingredientsList;
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

	public void setIngredientsList(IngredientsList ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	public void setMeasurement(Long measurement) {
		this.measurement = measurement;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
//	public void addMeasurementUnitOptions(Map<Integer, String> newOptions) {
//		for(Map.Entry e : newOptions.entrySet())
//			this.measurementUnitOptions.putIfAbsent((Integer)(e.getKey()), (String)(e.getValue()));
//	}

	public void setName(String name) {
		this.name = name;
	}
}