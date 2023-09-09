package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.BloodSugar;
import com.alive_backend.repository.health_data.BloodSugarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BloodSugarDaoImplTest {

    @Mock
    private BloodSugarRepository mockBloodSugarRepository;

    @InjectMocks
    private BloodSugarDaoImpl bloodSugarDaoImplUnderTest;

    @Test
    void testGetBloodSugarByDate() {
        // Setup
        // Configure BloodSugarRepository.findByUserIdAndDateBetween(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("55237223-4ad9-444a-91ae-7dca9e50bfb7"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<BloodSugar> bloodSugars = Arrays.asList(bloodSugar);
        when(mockBloodSugarRepository.findByUserIdAndDateBetween(0,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(bloodSugars);

        // Run the test
        final List<BloodSugar> result = bloodSugarDaoImplUnderTest.getBloodSugarByDate(0,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Verify the results
    }

    @Test
    void testGetBloodSugarByDate_BloodSugarRepositoryReturnsNoItems() {
        // Setup
        when(mockBloodSugarRepository.findByUserIdAndDateBetween(0,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Run the test
        final List<BloodSugar> result = bloodSugarDaoImplUnderTest.getBloodSugarByDate(0,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testAddBloodSugar() {
        // Setup
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("55237223-4ad9-444a-91ae-7dca9e50bfb7"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure BloodSugarRepository.save(...).
        final BloodSugar bloodSugar1 = new BloodSugar();
        bloodSugar1.setUserId(0);
        bloodSugar1.setId(UUID.fromString("55237223-4ad9-444a-91ae-7dca9e50bfb7"));
        bloodSugar1.setBloodSugar(0.0);
        bloodSugar1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarRepository.save(any(BloodSugar.class))).thenReturn(bloodSugar1);

        // Run the test
        final BloodSugar result = bloodSugarDaoImplUnderTest.addBloodSugar(bloodSugar);

        // Verify the results
    }

    @Test
    void testGetLatestBloodSugar() {
        // Setup
        // Configure BloodSugarRepository.findTopByUserIdOrderByDateDesc(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("55237223-4ad9-444a-91ae-7dca9e50bfb7"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarRepository.findTopByUserIdOrderByDateDesc(0)).thenReturn(bloodSugar);

        // Run the test
        final BloodSugar result = bloodSugarDaoImplUnderTest.getLatestBloodSugar(0);

        // Verify the results
    }
}
