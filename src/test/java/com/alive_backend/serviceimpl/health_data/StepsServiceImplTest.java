package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.StepsDao;
import com.alive_backend.entity.health_data.Steps;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StepsServiceImplTest {

    @Mock
    private StepsDao mockStepsDao;

    @InjectMocks
    private StepsServiceImpl stepsServiceImplUnderTest;

    @Test
    void testAddSteps() {
        // Setup
        final Steps steps = new Steps();
        steps.setId(UUID.fromString("949b02b6-6a2d-4a39-b537-13411de8933e"));
        steps.setUserId(0);
        steps.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps.setStep(0);
        steps.setGoal(0);

        // Configure StepsDao.addSteps(...).
        final Steps steps1 = new Steps();
        steps1.setId(UUID.fromString("949b02b6-6a2d-4a39-b537-13411de8933e"));
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);
        when(mockStepsDao.addSteps(any(Steps.class))).thenReturn(steps1);

        // Run the test
        final Steps result = stepsServiceImplUnderTest.addSteps(steps);

        // Verify the results
    }

    @Test
    void testFindByUserIdAndDate() {
        // Setup
        // Configure StepsDao.findByUserIdAndDate(...).
        final Steps steps = new Steps();
        steps.setId(UUID.fromString("949b02b6-6a2d-4a39-b537-13411de8933e"));
        steps.setUserId(0);
        steps.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps.setStep(0);
        steps.setGoal(0);
        when(mockStepsDao.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(steps);

        // Run the test
        final Steps result = stepsServiceImplUnderTest.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        final Steps steps1 = new Steps();
        steps1.setId(UUID.fromString("949b02b6-6a2d-4a39-b537-13411de8933e"));
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);

        // Run the test
        stepsServiceImplUnderTest.delete(steps1);

        // Verify the results
        verify(mockStepsDao).delete(any(Steps.class));
    }

    @Test
    void testGetSteps() {
        // Setup
        // Configure StepsDao.getSteps(...).
        final Steps steps1 = new Steps();
        steps1.setId(UUID.fromString("949b02b6-6a2d-4a39-b537-13411de8933e"));
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);
        final List<Steps> steps = Arrays.asList(steps1);
        when(mockStepsDao.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(steps);

        // Run the test
        final List<Steps> result = stepsServiceImplUnderTest.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testGetSteps_StepsDaoReturnsNoItems() {
        // Setup
        when(mockStepsDao.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Steps> result = stepsServiceImplUnderTest.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
