package tws.rsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tws.rsi.model.Ingredient;

@Repository("ingredientRepository")
public interface IngredientRepository  extends JpaRepository<Ingredient, Long> {
	
	List<Ingredient> findAllIngredients();
}
