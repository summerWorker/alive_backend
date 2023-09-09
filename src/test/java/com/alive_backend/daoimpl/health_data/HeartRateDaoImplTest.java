package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.HeartRate;
import com.alive_backend.repository.health_data.HeartRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeartRateDaoImplTest {

    @Mock
    private HeartRateRepository mockHeartRateRepository;

    @InjectMocks
    private HeartRateDaoImpl heartRateDaoImplUnderTest;

    @Test
    void testFindHeartRateByUserIdAndTimeStampBetween() {
        // Setup
        // Configure HeartRateRepository.findByUserIdAndTimeStampBetween(...).
        final HeartRate heartRate = new HeartRate();
        heartRate.setId(0);
        heartRate.setUserId(0);
        heartRate.setTimeStamp(0L);
        heartRate.setDetailValue("detailValue");
        final List<HeartRate> heartRates = Arrays.asList(heartRate);
        when(mockHeartRateRepository.findByUserIdAndTimeStampBetween(0, 0L, 0L)).thenReturn(heartRates);

        // Run the test
        final List<HeartRate> result = heartRateDaoImplUnderTest.findHeartRateByUserIdAndTimeStampBetween(0, 0L, 0L);

        // Verify the results
    }

    @Test
    void testFindHeartRateByUserIdAndTimeStampBetween_HeartRateRepositoryReturnsNoItems() {
        // Setup
        when(mockHeartRateRepository.findByUserIdAndTimeStampBetween(0, 0L, 0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<HeartRate> result = heartRateDaoImplUnderTest.findHeartRateByUserIdAndTimeStampBetween(0, 0L, 0L);

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
        heartRateDaoImplUnderTest.addHeartRate(newHeartRate);

        // Verify the results
        verify(mockHeartRateRepository).save(any(HeartRate.class));
    }
}
