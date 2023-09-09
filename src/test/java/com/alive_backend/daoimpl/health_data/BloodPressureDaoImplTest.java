package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.BloodPressure;
import com.alive_backend.repository.health_data.BloodPressureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BloodPressureDaoImplTest {

    @Mock
    private BloodPressureRepository mockBloodPressureRepository;

    @InjectMocks
    private BloodPressureDaoImpl bloodPressureDaoImplUnderTest;

    @Test
    void testGetBloodPressureByDate() {
        // Setup
        // Configure BloodPressureRepository.findByUserIdAndDate(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("65190267-9012-4630-91c8-d85432ed14db"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureRepository.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(bloodPressure);

        // Run the test
        final BloodPressure result = bloodPressureDaoImplUnderTest.getBloodPressureByDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testAddBloodPressure() {
        // Setup
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("65190267-9012-4630-91c8-d85432ed14db"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure BloodPressureRepository.save(...).
        final BloodPressure bloodPressure1 = new BloodPressure();
        bloodPressure1.setUserId(0);
        bloodPressure1.setId(UUID.fromString("65190267-9012-4630-91c8-d85432ed14db"));
        bloodPressure1.setSystolic(0);
        bloodPressure1.setDiastolic(0);
        bloodPressure1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureRepository.save(any(BloodPressure.class))).thenReturn(bloodPressure1);

        // Run the test
        final BloodPressure result = bloodPressureDaoImplUnderTest.addBloodPressure(bloodPressure);

        // Verify the results
    }

    @Test
    void testGetLatestBloodPressure() {
        // Setup
        // Configure BloodPressureRepository.findTopByUserIdOrderByDateDesc(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("65190267-9012-4630-91c8-d85432ed14db"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureRepository.findTopByUserIdOrderByDateDesc(0)).thenReturn(bloodPressure);

        // Run the test
        final BloodPressure result = bloodPressureDaoImplUnderTest.getLatestBloodPressure(0);

        // Verify the results
    }
}
