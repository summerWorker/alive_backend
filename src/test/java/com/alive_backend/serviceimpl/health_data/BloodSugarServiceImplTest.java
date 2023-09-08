package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodSugarDao;
import com.alive_backend.entity.health_data.BloodSugar;
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
class BloodSugarServiceImplTest {

    @Mock
    private BloodSugarDao mockBloodSugarDao;

    @InjectMocks
    private BloodSugarServiceImpl bloodSugarServiceImplUnderTest;

    @Test
    void testGetBloodSugarByDate() {
        // Setup
        // Configure BloodSugarDao.getBloodSugarByDate(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("5427300d-9f08-4b53-b939-ae654bcd515f"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<BloodSugar> bloodSugars = Arrays.asList(bloodSugar);
        when(mockBloodSugarDao.getBloodSugarByDate(0, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(bloodSugars);

        // Run the test
        final List<BloodSugar> result = bloodSugarServiceImplUnderTest.getBloodSugarByDate(0,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Verify the results
    }

    @Test
    void testGetBloodSugarByDate_BloodSugarDaoReturnsNoItems() {
        // Setup
        when(mockBloodSugarDao.getBloodSugarByDate(0, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Run the test
        final List<BloodSugar> result = bloodSugarServiceImplUnderTest.getBloodSugarByDate(0,
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
        bloodSugar.setId(UUID.fromString("5427300d-9f08-4b53-b939-ae654bcd515f"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure BloodSugarDao.addBloodSugar(...).
        final BloodSugar bloodSugar1 = new BloodSugar();
        bloodSugar1.setUserId(0);
        bloodSugar1.setId(UUID.fromString("5427300d-9f08-4b53-b939-ae654bcd515f"));
        bloodSugar1.setBloodSugar(0.0);
        bloodSugar1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarDao.addBloodSugar(any(BloodSugar.class))).thenReturn(bloodSugar1);

        // Run the test
        final BloodSugar result = bloodSugarServiceImplUnderTest.addBloodSugar(bloodSugar);

        // Verify the results
    }

    @Test
    void testGetLatestBloodSugar() {
        // Setup
        // Configure BloodSugarDao.getLatestBloodSugar(...).
        final BloodSugar bloodSugar = new BloodSugar();
        bloodSugar.setUserId(0);
        bloodSugar.setId(UUID.fromString("5427300d-9f08-4b53-b939-ae654bcd515f"));
        bloodSugar.setBloodSugar(0.0);
        bloodSugar.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockBloodSugarDao.getLatestBloodSugar(0)).thenReturn(bloodSugar);

        // Run the test
        final BloodSugar result = bloodSugarServiceImplUnderTest.getLatestBloodSugar(0);

        // Verify the results
    }
}
