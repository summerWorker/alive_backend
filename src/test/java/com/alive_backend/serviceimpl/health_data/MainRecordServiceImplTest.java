package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.MainRecordDao;
import com.alive_backend.entity.health_data.MainRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainRecordServiceImplTest {

    @Mock
    private MainRecordDao mockMainRecordDao;

    @InjectMocks
    private MainRecordServiceImpl mainRecordServiceImplUnderTest;

    @Test
    void testGetMainRecordByUserId() {
        // Setup
        final MainRecord expectedResult = new MainRecord();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setWeight(0.0);
        expectedResult.setExerciseTime(0.0);
        expectedResult.setCalorieIn(0.0);

        // Configure MainRecordDao.getMainRecordByUserId(...).
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setExerciseTime(0.0);
        mainRecord.setCalorieIn(0.0);
        when(mockMainRecordDao.getMainRecordByUserId(0)).thenReturn(mainRecord);

        // Run the test
        final MainRecord result = mainRecordServiceImplUnderTest.getMainRecordByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateMainRecord() {
        // Setup
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setExerciseTime(0.0);
        mainRecord.setCalorieIn(0.0);

        final MainRecord expectedResult = new MainRecord();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setWeight(0.0);
        expectedResult.setExerciseTime(0.0);
        expectedResult.setCalorieIn(0.0);

        // Configure MainRecordDao.updateMainRecord(...).
        final MainRecord mainRecord1 = new MainRecord();
        mainRecord1.setUserId(0);
        mainRecord1.setHeight(0.0);
        mainRecord1.setWeight(0.0);
        mainRecord1.setExerciseTime(0.0);
        mainRecord1.setCalorieIn(0.0);
        final MainRecord mainRecord2 = new MainRecord();
        mainRecord2.setUserId(0);
        mainRecord2.setHeight(0.0);
        mainRecord2.setWeight(0.0);
        mainRecord2.setExerciseTime(0.0);
        mainRecord2.setCalorieIn(0.0);
        when(mockMainRecordDao.updateMainRecord(mainRecord2)).thenReturn(mainRecord1);

        // Run the test
        final MainRecord result = mainRecordServiceImplUnderTest.updateMainRecord(mainRecord);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
