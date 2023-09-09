package com.alive_backend.daoimpl.goal;

import com.alive_backend.entity.goal.Goal;
import com.alive_backend.repository.goal.GoalRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalDaoImplTest {

    @Mock
    private GoalRepository mockGoalRepository;

    @InjectMocks
    private GoalDaoImpl goalDaoImplUnderTest;

    @Test
    void testGetGoalByGoalName() {
        // Setup
        final Goal expectedResult = new Goal();
        expectedResult.setUserId(0);
        expectedResult.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        expectedResult.setGoalName("goalName");
        expectedResult.setGoalKey1(0.0);
        expectedResult.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure GoalRepository.findByUserIdAndGoalName(...).
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockGoalRepository.findByUserIdAndGoalName(0, "goalName")).thenReturn(goal);

        // Run the test
        final Goal result = goalDaoImplUnderTest.getGoalByGoalName(0, "goalName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGoalsByUserId() {
        // Setup
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Goal> expectedResult = Arrays.asList(goal);

        // Configure GoalRepository.findByUserId(...).
        final Goal goal1 = new Goal();
        goal1.setUserId(0);
        goal1.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        goal1.setGoalName("goalName");
        goal1.setGoalKey1(0.0);
        goal1.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Goal> goals = Arrays.asList(goal1);
        when(mockGoalRepository.findByUserId(0)).thenReturn(goals);

        // Run the test
        final List<Goal> result = goalDaoImplUnderTest.getGoalsByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGoalsByUserId_GoalRepositoryReturnsNoItems() {
        // Setup
        when(mockGoalRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goal> result = goalDaoImplUnderTest.getGoalsByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSetGoal() {
        // Setup
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final Goal expectedResult = new Goal();
        expectedResult.setUserId(0);
        expectedResult.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        expectedResult.setGoalName("goalName");
        expectedResult.setGoalKey1(0.0);
        expectedResult.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure GoalRepository.save(...).
        final Goal goal1 = new Goal();
        goal1.setUserId(0);
        goal1.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        goal1.setGoalName("goalName");
        goal1.setGoalKey1(0.0);
        goal1.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Goal entity = new Goal();
        entity.setUserId(0);
        entity.setUuid(UUID.fromString("3f229281-f4ea-4f3b-8182-8d0cee4739a1"));
        entity.setGoalName("goalName");
        entity.setGoalKey1(0.0);
        entity.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockGoalRepository.save(entity)).thenReturn(goal1);

        // Run the test
        final Goal result = goalDaoImplUnderTest.setGoal(goal);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
