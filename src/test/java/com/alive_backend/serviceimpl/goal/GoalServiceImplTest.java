package com.alive_backend.serviceimpl.goal;

import com.alive_backend.dao.goal.GoalDao;
import com.alive_backend.entity.goal.Goal;
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
class GoalServiceImplTest {

    @Mock
    private GoalDao mockGoalDao;

    @InjectMocks
    private GoalServiceImpl goalServiceImplUnderTest;

    @Test
    void testGetGoalByGoalName() {
        // Setup
        final Goal expectedResult = new Goal();
        expectedResult.setUserId(0);
        expectedResult.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        expectedResult.setGoalName("goalName");
        expectedResult.setGoalKey1(0.0);
        expectedResult.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure GoalDao.getGoalByGoalName(...).
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockGoalDao.getGoalByGoalName(0, "goalName")).thenReturn(goal);

        // Run the test
        final Goal result = goalServiceImplUnderTest.getGoalByGoalName(0, "goalName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetGoal() {
        // Setup
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        final Goal expectedResult = new Goal();
        expectedResult.setUserId(0);
        expectedResult.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        expectedResult.setGoalName("goalName");
        expectedResult.setGoalKey1(0.0);
        expectedResult.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Configure GoalDao.setGoal(...).
        final Goal goal1 = new Goal();
        goal1.setUserId(0);
        goal1.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal1.setGoalName("goalName");
        goal1.setGoalKey1(0.0);
        goal1.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final Goal goal2 = new Goal();
        goal2.setUserId(0);
        goal2.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal2.setGoalName("goalName");
        goal2.setGoalKey1(0.0);
        goal2.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockGoalDao.setGoal(goal2)).thenReturn(goal1);

        // Run the test
        final Goal result = goalServiceImplUnderTest.setGoal(goal);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGoalsByUserId() {
        // Setup
        final Goal goal = new Goal();
        goal.setUserId(0);
        goal.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal.setGoalName("goalName");
        goal.setGoalKey1(0.0);
        goal.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Goal> expectedResult = Arrays.asList(goal);

        // Configure GoalDao.getGoalsByUserId(...).
        final Goal goal1 = new Goal();
        goal1.setUserId(0);
        goal1.setUuid(UUID.fromString("52a9c0f2-64b3-44c8-b683-b1f501e09791"));
        goal1.setGoalName("goalName");
        goal1.setGoalKey1(0.0);
        goal1.setGoalDdl(Date.valueOf(LocalDate.of(2020, 1, 1)));
        final List<Goal> goals = Arrays.asList(goal1);
        when(mockGoalDao.getGoalsByUserId(0)).thenReturn(goals);

        // Run the test
        final List<Goal> result = goalServiceImplUnderTest.getGoalsByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetGoalsByUserId_GoalDaoReturnsNoItems() {
        // Setup
        when(mockGoalDao.getGoalsByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goal> result = goalServiceImplUnderTest.getGoalsByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
