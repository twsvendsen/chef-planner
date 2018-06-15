package tws.rsi.service;

import java.util.List;

import tws.rsi.model.Ingredient;

public interface IngredientService {

	Ingredient save(Ingredient ingredient);
	
	Ingredient findById(Long id);
	
	List<Ingredient> findAllIngredients();
	
	void delete(Long id);
}
