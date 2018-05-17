package tws.rsi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tws.rsi.model.IngredientsList;
import tws.rsi.repository.IngredientsListRepository;

@Service("ingredientsListService")
public class IngredientsListServiceImpl implements IngredientsListService {

	@Autowired
	IngredientsListRepository ingredientsListRepository;
	
	@Override
	@Transactional
	public IngredientsList save(IngredientsList ingredientsList) {
		return ingredientsListRepository.save(ingredientsList);
	}

	@Override
	public IngredientsList findById(Long id) {
		return ingredientsListRepository.findOne(id);
	}

	@Override
	public List<IngredientsList> findAllIngredientsLists() {
		return ingredientsListRepository.findAllIngredientsLists();
	}

}
