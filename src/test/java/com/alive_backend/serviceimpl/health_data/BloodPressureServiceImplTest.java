package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.BloodPressureDao;
import com.alive_backend.entity.health_data.BloodPressure;
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
class BloodPressureServiceImplTest {

    @Mock
    private BloodPressureDao mockBloodPressureDao;

    @InjectMocks
    private BloodPressureServiceImpl bloodPressureServiceImplUnderTest;

    @Test
    void testGetBloodPressureByDate() {
        // Setup
        // Configure BloodPressureDao.getBloodPressureByDate(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("8fc29bec-ead2-4e3b-82ca-0d00bd338715"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureDao.getBloodPressureByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(bloodPressure);

        // Run the test
        final BloodPressure result = bloodPressureServiceImplUnderTest.getBloodPressureByDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testAddBloodPressure() {
        // Setup
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("8fc29bec-ead2-4e3b-82ca-0d00bd338715"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure BloodPressureDao.addBloodPressure(...).
        final BloodPressure bloodPressure1 = new BloodPressure();
        bloodPressure1.setUserId(0);
        bloodPressure1.setId(UUID.fromString("8fc29bec-ead2-4e3b-82ca-0d00bd338715"));
        bloodPressure1.setSystolic(0);
        bloodPressure1.setDiastolic(0);
        bloodPressure1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureDao.addBloodPressure(any(BloodPressure.class))).thenReturn(bloodPressure1);

        // Run the test
        final BloodPressure result = bloodPressureServiceImplUnderTest.addBloodPressure(bloodPressure);

        // Verify the results
    }

    @Test
    void testGetLatestBloodPressure() {
        // Setup
        // Configure BloodPressureDao.getLatestBloodPressure(...).
        final BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setUserId(0);
        bloodPressure.setId(UUID.fromString("8fc29bec-ead2-4e3b-82ca-0d00bd338715"));
        bloodPressure.setSystolic(0);
        bloodPressure.setDiastolic(0);
        bloodPressure.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockBloodPressureDao.getLatestBloodPressure(0)).thenReturn(bloodPressure);

        // Run the test
        final BloodPressure result = bloodPressureServiceImplUnderTest.getLatestBloodPressure(0);

        // Verify the results
    }
}
