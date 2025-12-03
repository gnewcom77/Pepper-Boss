package pepper.boss.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import pepper.boss.controller.DTO.PepperDTO;
import pepper.boss.dao.PepperDao;
import pepper.boss.entity.Pepper;
import pepper.boss.error.ResourceNotFoundException;
import pepper.boss.mapper.EntityMapper;
import pepper.boss.service.PepperService;

@RestController
@RequestMapping("/peppers")
public class PepperController {

	private final PepperService pepperService;


	public PepperController(PepperService pepperService) {
	    this.pepperService = pepperService;
	}

	// ===== Basic CRUD returning entities =====

	@GetMapping
	public List<Pepper> fetchPeppers() {
		return pepperService.findAll();
	}

	@GetMapping("/{id}")
	public Pepper fetchPepperById(@PathVariable Long id) {
		return pepperService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pepper createPepper(@Valid @RequestBody Pepper body) {
		return pepperService.create(body);
	}

	@PutMapping("/{id}")
	public Pepper updatePepper(@PathVariable Long id, @Valid @RequestBody Pepper body) {
		return pepperService.update(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePepper(@PathVariable Long id) {
		pepperService.delete(id);
	}


	@GetMapping("/dto")
	public List<PepperDTO> fetchPepperDTOs() {
	    return pepperService.findAll()
	            .stream()
	            .map(EntityMapper::toPepperDTO)
	            .toList();
	}

	@GetMapping("/{id}/dto")
	public PepperDTO fetchPepperDTOById(@PathVariable Long id) {
	    Pepper pepper = pepperService.findById(id);
	    return EntityMapper.toPepperDTO(pepper);
	}
}