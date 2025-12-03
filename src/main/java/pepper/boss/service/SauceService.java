package pepper.boss.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pepper.boss.dao.IngredientDao;
import pepper.boss.dao.PepperDao;
import pepper.boss.dao.SauceDao;
import pepper.boss.entity.Ingredient;
import pepper.boss.entity.Pepper;
import pepper.boss.entity.Sauce;
import pepper.boss.error.ResourceNotFoundException;

@Service
public class SauceService {

	private final SauceDao sauceDao;
	private final PepperDao pepperDao;
	private final IngredientDao ingredientDao;

	public SauceService(SauceDao sauceDao, PepperDao pepperDao, IngredientDao ingredientDao) {
		this.sauceDao = sauceDao;
		this.pepperDao = pepperDao;
		this.ingredientDao = ingredientDao;

	}

	public List<Sauce> findAll() {
		return sauceDao.findAll();
	}

	public Sauce findById(Long id) {
		return sauceDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sauce " + id + " not found"));

	}

	@Transactional
	public Sauce create(String name, String style, String heatLevel, String notes, Long pepperId,
			List<Long> ingredientIds) {

		Pepper pepper = pepperDao.findById(pepperId)
				.orElseThrow(() -> new ResourceNotFoundException("Pepper " + pepperId + " not found"));

		Sauce s = new Sauce();
		s.setName(name);
		s.setStyle(style);
		s.setHeatLevel(heatLevel);
		s.setNotes(notes);
		s.setPepper(pepper);

		if (ingredientIds != null && !ingredientIds.isEmpty()) {
			Set<Ingredient> ingredients = new HashSet<>(ingredientDao.findAllById(ingredientIds));
			if (ingredients.size() != ingredientIds.size()) {
				throw new ResourceNotFoundException("One or more ingredientIds not found");
			}
			s.setIngredients(ingredients);
		}

		return sauceDao.save(s);
	}

	@Transactional
	public Sauce update(Long id, String name, String style, String heatLevel, String notes, Long pepperId,
			List<Long> ingredientIds) {

		Sauce s = findById(id);

		if (name != null)
			s.setName(name);
		if (style != null)
			s.setStyle(style);
		if (heatLevel != null)
			s.setHeatLevel(heatLevel);
		if (notes != null)
			s.setNotes(notes);

		if (pepperId != null) {
			Pepper pepper = pepperDao.findById(pepperId)
					.orElseThrow(() -> new ResourceNotFoundException("Pepper " + pepperId + " not found"));
			s.setPepper(pepper);
		}

		if (ingredientIds != null) {
			Set<Ingredient> ingredients = new HashSet<>(ingredientDao.findAllById(ingredientIds));
			if (ingredients.size() != ingredientIds.size()) {
				throw new ResourceNotFoundException("One or more ingredientIds not found");
			}
			s.setIngredients(ingredients);
		}

		return sauceDao.save(s);
	}

	public void delete(Long id) {
		if (!sauceDao.existsById(id)) {
			throw new ResourceNotFoundException("Sauce " + id + " not found");
		}
		sauceDao.deleteById(id);
	}

	@Transactional
	public Sauce addIngredients(Long sauceId, List<Long> ingredientIds) {
		Sauce s = findById(sauceId);
		Set<Ingredient> add = new HashSet<>(ingredientDao.findAllById(ingredientIds));
		if (add.size() != ingredientIds.size()) {
			throw new ResourceNotFoundException("One or more ingredientIds not found");
		}
		s.getIngredients().addAll(add);
		return sauceDao.save(s);
	}

	@Transactional
	public void removeIngredient(Long sauceId, Long ingredientId) {
		Sauce s = findById(sauceId);
		boolean removed = s.getIngredients().removeIf(i -> i.getIngredientId().equals(ingredientId));
		if (!removed) {
			throw new ResourceNotFoundException("Ingredient " + ingredientId + " not linked to Sauce " + sauceId);
		}
		sauceDao.save(s);
	}
	public List<Sauce> findByPepperId(Long pepperId) {
		  pepperDao.findById(pepperId)
		      .orElseThrow(() -> new pepper.boss.error.ResourceNotFoundException("Pepper " + pepperId + " not found"));
		  return sauceDao.findByPepper_PepperId(pepperId);
		}
}