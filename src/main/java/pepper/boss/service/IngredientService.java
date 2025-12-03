package pepper.boss.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pepper.boss.dao.IngredientDao;
import pepper.boss.entity.Ingredient;
import pepper.boss.error.ResourceNotFoundException;

@Service
@Transactional
public class IngredientService {

	private final IngredientDao ingredientDao;

	public IngredientService(IngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}

	public List<Ingredient> findAll() {
		return ingredientDao.findAll();
	}

	public Ingredient findById(Long id) {
		return ingredientDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ingredient " + id + " not found"));
	}

	public Ingredient create(Ingredient ingredient) {
		return ingredientDao.save(ingredient);
	}

	public Ingredient update(Long id, Ingredient body) {
		Ingredient existing = ingredientDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ingredient " + id + " not found"));

		existing.setName(body.getName());
		existing.setNotes(body.getNotes());

		return ingredientDao.save(existing);
	}

	public void delete(Long id) {
		if (!ingredientDao.existsById(id)) {
			throw new ResourceNotFoundException("Ingredient " + id + " not found");
		}
		ingredientDao.deleteById(id);
	}
}