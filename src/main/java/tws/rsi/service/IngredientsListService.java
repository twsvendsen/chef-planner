package tws.rsi.service;

import java.util.List;

import tws.rsi.model.IngredientsList;

public interface IngredientsListService {

	IngredientsList save(IngredientsList ingredientsList);
	
	IngredientsList findById(Long id);
	
	List<IngredientsList> findAllIngredientsLists();

}
