package tws.rsi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tws.rsi.model.Ingredient;
import tws.rsi.repository.IngredientRepository;

@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;
	
	@Override
	@Transactional
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}

	@Override
	public Ingredient findById(Long id) {
		return ingredientRepository.findOne(id);
	}

	@Override
	public List<Ingredient> findAllIngredients() {
		return ingredientRepository.findAllIngredients();
	}

}
