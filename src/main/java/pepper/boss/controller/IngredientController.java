package pepper.boss.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pepper.boss.controller.DTO.IngredientDTO;
import pepper.boss.entity.Ingredient;
import pepper.boss.mapper.EntityMapper;
import pepper.boss.service.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;

	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public List<Ingredient> fetchIngredients() {
		return ingredientService.findAll();
	}

	@GetMapping("/{id}")
	public Ingredient fetchIngredientById(@PathVariable Long id) {
		return ingredientService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient createIngredient(@Valid @RequestBody Ingredient body) {
		return ingredientService.create(body);
	}

	@PutMapping("/{id}")
	public Ingredient updateIngredient(@PathVariable Long id, @Valid @RequestBody Ingredient body) {
		return ingredientService.update(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngredient(@PathVariable Long id) {
		ingredientService.delete(id);
	}

	@GetMapping("/dto")
	public List<IngredientDTO> fetchIngredientsDto() {
		Iterable<Ingredient> items = ingredientService.findAll();
		List<IngredientDTO> result = new ArrayList<>();
		for (Ingredient i : items) {
			result.add(EntityMapper.toDTO(i)); // uses toDTO(Ingredient)
		}
		return result;
	}

	@GetMapping("/dto/{id}")
	public IngredientDTO fetchIngredientDtoById(@PathVariable Long id) {
		Ingredient i = ingredientService.findById(id);
		return EntityMapper.toDTO(i); // uses toDTO(Ingredient)
	}
}
