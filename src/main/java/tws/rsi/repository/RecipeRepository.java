package tws.rsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tws.rsi.model.Recipe;

@Repository("recipeRepository")
public interface RecipeRepository extends JpaRepository<Recipe, Long>
{
	List<Recipe> findAllRecipes();  // this method name links by-name to the same-named Named Query in Recipe.java
}
