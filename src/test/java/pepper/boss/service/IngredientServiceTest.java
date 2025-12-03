package pepper.boss.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pepper.boss.dao.IngredientDao;
import pepper.boss.entity.Ingredient;
import pepper.boss.error.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

	@Mock
	private IngredientDao ingredientDao;

	@Test
	void findById_returnsIngredient_whenFound() {

		IngredientService ingredientService = new IngredientService(ingredientDao);

		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(1L);
		ingredient.setName("Garlic");

		when(ingredientDao.findById(1L)).thenReturn(Optional.of(ingredient));

		Ingredient result = ingredientService.findById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getIngredientId());
		assertEquals("Garlic", result.getName());
	}

	@Test
	void findById_throwsResourceNotFound_whenMissing() {

		IngredientService ingredientService = new IngredientService(ingredientDao);
		when(ingredientDao.findById(99L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> ingredientService.findById(99L));
	}

	@Test
	void create_savesIngredient() {
		IngredientService ingredientService = new IngredientService(ingredientDao);

		Ingredient toSave = new Ingredient();
		toSave.setName("Lime juice");

		Ingredient saved = new Ingredient();
		saved.setIngredientId(5L);
		saved.setName("Lime juice");

		when(ingredientDao.save(toSave)).thenReturn(saved);

		Ingredient result = ingredientService.create(toSave);

		assertNotNull(result.getIngredientId());
		assertEquals("Lime juice", result.getName());
		verify(ingredientDao).save(toSave);
	}
}
