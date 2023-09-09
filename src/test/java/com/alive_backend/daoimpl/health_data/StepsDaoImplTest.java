package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.Steps;
import com.alive_backend.repository.health_data.StepsRepository;
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
class StepsDaoImplTest {

    @Mock
    private StepsRepository mockStepsRepository;

    @InjectMocks
    private StepsDaoImpl stepsDaoImplUnderTest;

    @Test
    void testAddSteps() {
        // Setup
        final Steps steps = new Steps();
        steps.setId(UUID.fromString("b458977d-d135-41b6-b1fe-8616c8153be1"));
        steps.setUserId(0);
        steps.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps.setStep(0);
        steps.setGoal(0);

        // Configure StepsRepository.save(...).
        final Steps steps1 = new Steps();
        steps1.setId(UUID.fromString("b458977d-d135-41b6-b1fe-8616c8153be1"));
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);
        when(mockStepsRepository.save(any(Steps.class))).thenReturn(steps1);

        // Run the test
        final Steps result = stepsDaoImplUnderTest.addSteps(steps);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        final Steps steps = new Steps();
        steps.setId(UUID.fromString("b458977d-d135-41b6-b1fe-8616c8153be1"));
        steps.setUserId(0);
        steps.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps.setStep(0);
        steps.setGoal(0);

        // Run the test
        stepsDaoImplUnderTest.delete(steps);

        // Verify the results
        verify(mockStepsRepository).delete(any(Steps.class));
    }

    @Test
    void testFindByUserIdAndDate() {
        // Setup
        // Configure StepsRepository.findByUserIdAndDate(...).
        final Steps steps = new Steps();
        steps.setId(UUID.fromString("b458977d-d135-41b6-b1fe-8616c8153be1"));
        steps.setUserId(0);
        steps.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps.setStep(0);
        steps.setGoal(0);
        when(mockStepsRepository.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(steps);

        // Run the test
        final Steps result = stepsDaoImplUnderTest.findByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testGetSteps() {
        // Setup
        // Configure StepsRepository.findByUserIdAndDateBetween(...).
        final Steps steps1 = new Steps();
        steps1.setId(UUID.fromString("b458977d-d135-41b6-b1fe-8616c8153be1"));
        steps1.setUserId(0);
        steps1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        steps1.setStep(0);
        steps1.setGoal(0);
        final List<Steps> steps = Arrays.asList(steps1);
        when(mockStepsRepository.findByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(steps);

        // Run the test
        final List<Steps> result = stepsDaoImplUnderTest.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
    }

    @Test
    void testGetSteps_StepsRepositoryReturnsNoItems() {
        // Setup
        when(mockStepsRepository.findByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Steps> result = stepsDaoImplUnderTest.getSteps(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
