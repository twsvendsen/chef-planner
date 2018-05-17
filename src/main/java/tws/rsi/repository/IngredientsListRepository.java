package tws.rsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tws.rsi.model.IngredientsList;

@Repository("ingredientsListRepository")
public interface IngredientsListRepository extends JpaRepository<IngredientsList, Long>{

	List<IngredientsList> findAllIngredientsLists();
}
