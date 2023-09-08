package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.HeartRateDao;
import com.alive_backend.entity.health_data.HeartRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeartRateServiceImplTest {

    @Mock
    private HeartRateDao mockHeartRateDao;

    @InjectMocks
    private HeartRateServiceImpl heartRateServiceImplUnderTest;

    @Test
    void testFindHeartRateByUserIdAndDate() {
        // Setup
        // Configure HeartRateDao.findHeartRateByUserIdAndTimeStampBetween(...).
        final HeartRate heartRate = new HeartRate();
        heartRate.setId(0);
        heartRate.setUserId(0);
        heartRate.setTimeStamp(0L);
        heartRate.setDetailValue("detailValue");
        final List<HeartRate> heartRates = Arrays.asList(heartRate);
        when(mockHeartRateDao.findHeartRateByUserIdAndTimeStampBetween(eq(0), anyLong(),anyLong())).thenReturn(heartRates);

        // Run the test
        final List<HeartRate> result = heartRateServiceImplUnderTest.findHeartRateByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        // Validate the size of the result list
        assertThat(result).hasSize(1);
    }

    @Test
    void testFindHeartRateByUserIdAndDate_HeartRateDaoReturnsNoItems() {
        // Setup
        when(mockHeartRateDao.findHeartRateByUserIdAndTimeStampBetween(eq(0), anyLong(),anyLong())).thenReturn(Collections.emptyList());

        // Run the test
        final List<HeartRate> result = heartRateServiceImplUnderTest.findHeartRateByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindHeartRateByUserIdAndDateBetween() {
        // Setup
        // Configure HeartRateDao.findHeartRateByUserIdAndTimeStampBetween(...).
        final HeartRate heartRate = new HeartRate();
        heartRate.setId(0);
        heartRate.setUserId(0);
        heartRate.setTimeStamp(0L);
        heartRate.setDetailValue("detailValue");
        final List<HeartRate> heartRates = Arrays.asList(heartRate);
        when(mockHeartRateDao.findHeartRateByUserIdAndTimeStampBetween(eq(0), anyLong(),anyLong())).thenReturn(heartRates);

        // Run the test
        final List<HeartRate> result = heartRateServiceImplUnderTest.findHeartRateByUserIdAndDateBetween(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).hasSize(1);
    }

    @Test
    void testFindHeartRateByUserIdAndDateBetween_HeartRateDaoReturnsNoItems() {
        // Setup
        when(mockHeartRateDao.findHeartRateByUserIdAndTimeStampBetween(eq(0), anyLong(),anyLong())).thenReturn(Collections.emptyList());

        // Run the test
        final List<HeartRate> result = heartRateServiceImplUnderTest.findHeartRateByUserIdAndDateBetween(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testAddHeartRate() {
        // Setup
        final HeartRate newHeartRate = new HeartRate();
        newHeartRate.setId(0);
        newHeartRate.setUserId(0);
        newHeartRate.setTimeStamp(0L);
        newHeartRate.setDetailValue("detailValue");

        // Run the test
        heartRateServiceImplUnderTest.addHeartRate(newHeartRate);

        // Verify the results
        verify(mockHeartRateDao).addHeartRate(any(HeartRate.class));
    }
}
