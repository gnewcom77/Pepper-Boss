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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pepper.boss.controller.DTO.SauceDTO;
import pepper.boss.entity.Sauce;
import pepper.boss.mapper.EntityMapper;
import pepper.boss.service.SauceService;

@RestController
@RequestMapping("/sauces")
public class SauceController {
	
	private final SauceService service;

	  public SauceController(SauceService service) {
	    this.service = service;
	  }

	  @GetMapping
	  public List<Sauce> fetchSauces() {
	    return service.findAll();
	  }

	 
	  @GetMapping("/{id}")
	  public Sauce fetchSauceById(@PathVariable Long id) {
	    return service.findById(id);
	  }


	  @GetMapping("/by-pepper/{pepperId}")
	  public List<Sauce> listSaucesByPepper(@PathVariable Long pepperId) {
	    return service.findByPepperId(pepperId);
	  }
	  
	  @GetMapping("/by-pepper/{pepperId}/dto")
	  public List<SauceDTO> listSaucesByPepperDto(@PathVariable Long pepperId) {
	    List<Sauce> sauces = service.findByPepperId(pepperId);
	    List<SauceDTO> result = new ArrayList<>();
	    for (Sauce s : sauces) {
	      result.add(EntityMapper.toDTO(s));
	    }
	    return result;
	  }

	  @GetMapping("/dto")
	  public List<SauceDTO> fetchSaucesDto() {
	    Iterable<Sauce> items = service.findAll();
	    List<SauceDTO> result = new ArrayList<>();
	    for (Sauce s : items) {
	      result.add(EntityMapper.toDTO(s));
	    }
	    return result;
	  }

	  @GetMapping("/dto/{id}")
	  public SauceDTO fetchSauceDtoById(@PathVariable Long id) {
	    Sauce s = service.findById(id);
	    return EntityMapper.toDTO(s);
	  }

	  @PostMapping
	  @ResponseStatus(HttpStatus.CREATED)
	  public Sauce createSauce(@Valid @RequestBody SauceRequest body) {
	    return service.create(
	        body.name(), body.style(), body.heatLevel(), body.notes(),
	        body.pepperId(), body.ingredientIds()
	    );
	  }

	  @PutMapping("/{id}")
	  public Sauce updateSauce(@PathVariable Long id, @Valid @RequestBody SauceRequest body) {
	    return service.update(
	        id, body.name(), body.style(), body.heatLevel(), body.notes(),
	        body.pepperId(), body.ingredientIds()
	    );
	  }

	  @DeleteMapping("/{id}")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteSauce(@PathVariable Long id) {
	    service.delete(id);
	  }

	  @PostMapping("/{sauceId}/ingredients/{ingredientId}")
	  public SauceDTO addIngredientToSauce(@PathVariable Long sauceId, @PathVariable Long ingredientId) {
	    service.addIngredients(sauceId, List.of(ingredientId));
	    Sauce saved = service.findById(sauceId);
	    return EntityMapper.toDTO(saved);
	  }

	  @DeleteMapping("/{sauceId}/ingredients/{ingredientId}")
	  public SauceDTO removeIngredientFromSauce(@PathVariable Long sauceId, @PathVariable Long ingredientId) {
	    service.removeIngredient(sauceId, ingredientId);
	    Sauce saved = service.findById(sauceId);
	    return EntityMapper.toDTO(saved);
	  }

	  public static record SauceRequest(
	      @NotBlank(message = "Name is required")
	      @Size(max = 255, message = "Name must be ≤ 255 characters")
	      String name,

	      @Size(max = 255, message = "Style must be 255 characters or less")
	      String style,

	      @Size(max = 50, message = "Heat level must be 50 characters or less")
	      String heatLevel,

	      @Size(max = 500, message = "Notes must be 500 characters or less")
	      String notes,

	      @NotNull(message = "pepperId is required")
	      Long pepperId,

	      List<Long> ingredientIds
	  ) {}
	}
