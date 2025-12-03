package pepper.boss.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pepper.boss.dao.PepperDao;
import pepper.boss.entity.Pepper;
import pepper.boss.error.ResourceNotFoundException;

@Service
@Transactional
public class PepperService {
	private final PepperDao pepperDao;

	public PepperService(PepperDao pepperDao) {
		this.pepperDao = pepperDao;
	}

	public List<Pepper> findAll() {
		return pepperDao.findAll();
	}

	public Pepper findById(Long id) {
		return pepperDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pepper " + id + " not found"));
	}

	public Pepper create(Pepper pepper) {
		return pepperDao.save(pepper);
	}

	public Pepper update(Long id, Pepper body) {
		Pepper existing = pepperDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pepper " + id + " not found"));

		existing.setName(body.getName());
		existing.setHeatLevel(body.getHeatLevel());
		existing.setNotes(body.getNotes());

		return pepperDao.save(existing);
	}

	public void delete(Long id) {
		if (!pepperDao.existsById(id)) {
			throw new ResourceNotFoundException("Pepper " + id + " not found");
		}
		pepperDao.deleteById(id);
	}
}
