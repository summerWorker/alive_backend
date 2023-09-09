package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.MainRecord;
import com.alive_backend.repository.health_data.MainRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainRecordDaoImplTest {

    @Mock
    private MainRecordRepository mockMainRecordRepository;

    @InjectMocks
    private MainRecordDaoImpl mainRecordDaoImplUnderTest;

    @Test
    void testGetMainRecordByUserId() {
        // Setup
        final MainRecord expectedResult = new MainRecord();
        expectedResult.setUserId(0);
        expectedResult.setHeight(0.0);
        expectedResult.setWeight(0.0);
        expectedResult.setExerciseTime(0.0);
        expectedResult.setCalorieIn(0.0);

        // Configure MainRecordRepository.findByUserId(...).
        final MainRecord mainRecord = new MainRecord();
        mainRecord.setUserId(0);
        mainRecord.setHeight(0.0);
        mainRecord.setWeight(0.0);
        mainRecord.setExerciseTime(0.0);
        mainRecord.setCalorieIn(0.0);
        when(mockMainRecordRepository.findByUserId(0)).thenReturn(mainRecord);

        // Run the test
        final MainRecord result = mainRecordDaoImplUnderTest.getMainRecordByUserId(0);

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

        // Configure MainRecordRepository.save(...).
        final MainRecord mainRecord1 = new MainRecord();
        mainRecord1.setUserId(0);
        mainRecord1.setHeight(0.0);
        mainRecord1.setWeight(0.0);
        mainRecord1.setExerciseTime(0.0);
        mainRecord1.setCalorieIn(0.0);
        final MainRecord entity = new MainRecord();
        entity.setUserId(0);
        entity.setHeight(0.0);
        entity.setWeight(0.0);
        entity.setExerciseTime(0.0);
        entity.setCalorieIn(0.0);
        when(mockMainRecordRepository.save(entity)).thenReturn(mainRecord1);

        // Run the test
        final MainRecord result = mainRecordDaoImplUnderTest.updateMainRecord(mainRecord);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
