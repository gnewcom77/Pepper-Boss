package pepper.boss.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pepper.boss.dao.PepperDao;
import pepper.boss.entity.Pepper;
import pepper.boss.error.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PepperServiceTest {
	
	@Mock
    private PepperDao pepperDao;

	@Test
    void findById_returnsPepper_whenFound() {
        // create service with mocked DAO
        PepperService pepperService = new PepperService(pepperDao);

        // given
        Pepper pepper = new Pepper();
        pepper.setPepperId(1L);
        pepper.setName("Jalapeño");

        when(pepperDao.findById(1L)).thenReturn(Optional.of(pepper));

        // when
        Pepper result = pepperService.findById(1L);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getPepperId());
        assertEquals("Jalapeño", result.getName());
    }

    @Test
    void findById_throwsResourceNotFound_whenMissing() {
        // create service with mocked DAO
        PepperService pepperService = new PepperService(pepperDao);

        when(pepperDao.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pepperService.findById(99L));
    }

    @Test
    void create_savesPepper() {
        // create service with mocked DAO
        PepperService pepperService = new PepperService(pepperDao);

        Pepper toSave = new Pepper();
        toSave.setName("Habanero");

        Pepper saved = new Pepper();
        saved.setPepperId(5L);
        saved.setName("Habanero");

        when(pepperDao.save(toSave)).thenReturn(saved);

        Pepper result = pepperService.create(toSave);

        assertNotNull(result.getPepperId());
        assertEquals("Habanero", result.getName());
        verify(pepperDao).save(toSave);
    }
}