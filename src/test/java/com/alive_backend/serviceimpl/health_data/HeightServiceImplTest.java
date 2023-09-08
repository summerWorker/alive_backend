package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.HeightDao;
import com.alive_backend.entity.health_data.Height;
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
class HeightServiceImplTest {

    @Mock
    private HeightDao mockHeightDao;

    @InjectMocks
    private HeightServiceImpl heightServiceImplUnderTest;

    @Test
    void testGetHeightByDate() {
        // Setup
        final Height expectedResult = new Height();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure HeightDao.getHeightByDate(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightDao.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(height);

        // Run the test
        final Height result = heightServiceImplUnderTest.getHeightByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)));

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

        // Configure HeightDao.getHeightByUser(...).
        final Height height1 = new Height();
        height1.setUserId(0);
        height1.setHeight(0.0);
        height1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Height> heights = Arrays.asList(height1);
        when(mockHeightDao.getHeightByUser(0)).thenReturn(heights);

        // Run the test
        final List<Height> result = heightServiceImplUnderTest.getHeightByUser(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHeightByUser_HeightDaoReturnsNoItems() {
        // Setup
        when(mockHeightDao.getHeightByUser(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Height> result = heightServiceImplUnderTest.getHeightByUser(0);

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

        // Configure HeightDao.addHeight(...).
        final Height height1 = new Height();
        height1.setUserId(0);
        height1.setHeight(0.0);
        height1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Height height2 = new Height();
        height2.setUserId(0);
        height2.setHeight(0.0);
        height2.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightDao.addHeight(height2)).thenReturn(height1);

        // Run the test
        final Height result = heightServiceImplUnderTest.addHeight(height);

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

        // Configure HeightDao.getLatestHeight(...).
        final Height height = new Height();
        height.setUserId(0);
        height.setHeight(0.0);
        height.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockHeightDao.getLatestHeight(0)).thenReturn(height);

        // Run the test
        final Height result = heightServiceImplUnderTest.getLatestHeight(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
