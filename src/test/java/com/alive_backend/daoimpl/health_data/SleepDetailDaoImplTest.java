package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.SleepDetail;
import com.alive_backend.repository.health_data.SleepDetailRepository;
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
class SleepDetailDaoImplTest {

    @Mock
    private SleepDetailRepository mockSleepDetailRepository;

    @InjectMocks
    private SleepDetailDaoImpl sleepDetailDaoImplUnderTest;

    @Test
    void testGetSleepDetailByDate() {
        // Setup
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);
        final List<SleepDetail> expectedResult = Arrays.asList(sleepDetail);

        // Configure SleepDetailRepository.findByUserIdAndDateBetween(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail1);
        when(mockSleepDetailRepository.findByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(sleepDetails);

        // Run the test
        final List<SleepDetail> result = sleepDetailDaoImplUnderTest.getSleepDetailByDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSleepDetailByDate_SleepDetailRepositoryReturnsNoItems() {
        // Setup
        when(mockSleepDetailRepository.findByUserIdAndDateBetween(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final List<SleepDetail> result = sleepDetailDaoImplUnderTest.getSleepDetailByDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetSleepDetailByUserId() {
        // Setup
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);
        final List<SleepDetail> expectedResult = Arrays.asList(sleepDetail);

        // Configure SleepDetailRepository.findByUserId(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail1);
        when(mockSleepDetailRepository.findByUserId(0)).thenReturn(sleepDetails);

        // Run the test
        final List<SleepDetail> result = sleepDetailDaoImplUnderTest.getSleepDetailByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSleepDetailByUserId_SleepDetailRepositoryReturnsNoItems() {
        // Setup
        when(mockSleepDetailRepository.findByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<SleepDetail> result = sleepDetailDaoImplUnderTest.getSleepDetailByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSaveSleepDetail() {
        // Setup
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);

        final SleepDetail expectedResult = new SleepDetail();
        expectedResult.setId(0L);
        expectedResult.setUserId(0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setDetailValue("detailValue");
        expectedResult.setLength(0);

        // Configure SleepDetailRepository.save(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final SleepDetail entity = new SleepDetail();
        entity.setId(0L);
        entity.setUserId(0);
        entity.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        entity.setDetailValue("detailValue");
        entity.setLength(0);
        when(mockSleepDetailRepository.save(entity)).thenReturn(sleepDetail1);

        // Run the test
        final SleepDetail result = sleepDetailDaoImplUnderTest.saveSleepDetail(sleepDetail);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
