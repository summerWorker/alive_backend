package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.Height;
import com.alive_backend.repository.health_data.HeightRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeightDaoImplTest {

    @Mock
    private HeightRepository mockHeightRepository;

    @InjectMocks
    private HeightDaoImpl heightDaoImplUnderTest;

    @Test
    void testGetHeightByDate() {
        // Setup
        final Height expectedResult = new Height();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure HeightRepository.findByUserIdAndDate(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightRepository.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(height);

        // Run the test
        final Height result = heightDaoImplUnderTest.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHeightByUser() {
        // Setup
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Height> expectedResult = Arrays.asList(height);

        // Configure HeightRepository.findByUserId(...).
        final Height height1 = new Height();
        height1.setUserId(0);
        height1.setHeight(0.0);
        height1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Height> heights = Arrays.asList(height1);
        when(mockHeightRepository.findByUserId(0)).thenReturn(heights);

        // Run the test
        final List<Height> result = heightDaoImplUnderTest.getHeightByUser(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHeightByUser_HeightRepositoryReturnsNoItems() {
        // Setup
        when(mockHeightRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Height> result = heightDaoImplUnderTest.getHeightByUser(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testAddHeight() {
        // Setup
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final Height expectedResult = new Height();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure HeightRepository.save(...).
        final Height height1 = new Height();
        height1.setUserId(0);
        height1.setHeight(0.0);
        height1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Height entity = new Height();
        entity.setUserId(0);
        entity.setHeight(0.0);
        entity.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightRepository.save(entity)).thenReturn(height1);

        // Run the test
        final Height result = heightDaoImplUnderTest.addHeight(height);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetLatestHeight() {
        // Setup
        final Height expectedResult = new Height();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure HeightRepository.findTopByUserIdOrderByDateDesc(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightRepository.findTopByUserIdOrderByDateDesc(0)).thenReturn(height);

        // Run the test
        final Height result = heightDaoImplUnderTest.getLatestHeight(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
