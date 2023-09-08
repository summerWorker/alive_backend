package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.SleepDetailDao;
import com.alive_backend.entity.health_data.SleepDetail;
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
class SleepDetailServiceImplTest {

    @Mock
    private SleepDetailDao mockSleepDetailDao;

    @InjectMocks
    private SleepDetailServiceImpl sleepDetailServiceImplUnderTest;

    @Test
    void testGetWeekSleepDetail() {
        // Setup
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);
        final List<SleepDetail> expectedResult = Arrays.asList(sleepDetail);

        // Configure SleepDetailDao.getSleepDetailByUserId(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail1);
        when(mockSleepDetailDao.getSleepDetailByUserId(0)).thenReturn(sleepDetails);

        // Run the test
        final List<SleepDetail> result = sleepDetailServiceImplUnderTest.getWeekSleepDetail(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetWeekSleepDetail_SleepDetailDaoReturnsNoItems() {
        // Setup
        when(mockSleepDetailDao.getSleepDetailByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<SleepDetail> result = sleepDetailServiceImplUnderTest.getWeekSleepDetail(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetDaySleepDetail() {
        // Setup
        final SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setId(0L);
        sleepDetail.setUserId(0);
        sleepDetail.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail.setDetailValue("detailValue");
        sleepDetail.setLength(0);
        final List<SleepDetail> expectedResult = Arrays.asList(sleepDetail);

        // Configure SleepDetailDao.getSleepDetailByDate(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final List<SleepDetail> sleepDetails = Arrays.asList(sleepDetail1);
        when(mockSleepDetailDao.getSleepDetailByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(sleepDetails);

        // Run the test
        final List<SleepDetail> result = sleepDetailServiceImplUnderTest.getDaySleepDetail(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDaySleepDetail_SleepDetailDaoReturnsNoItems() {
        // Setup
        when(mockSleepDetailDao.getSleepDetailByDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(Collections.emptyList());

        // Run the test
        final List<SleepDetail> result = sleepDetailServiceImplUnderTest.getDaySleepDetail(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));

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

        // Configure SleepDetailDao.saveSleepDetail(...).
        final SleepDetail sleepDetail1 = new SleepDetail();
        sleepDetail1.setId(0L);
        sleepDetail1.setUserId(0);
        sleepDetail1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail1.setDetailValue("detailValue");
        sleepDetail1.setLength(0);
        final SleepDetail sleepDetail2 = new SleepDetail();
        sleepDetail2.setId(0L);
        sleepDetail2.setUserId(0);
        sleepDetail2.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        sleepDetail2.setDetailValue("detailValue");
        sleepDetail2.setLength(0);
        when(mockSleepDetailDao.saveSleepDetail(sleepDetail2)).thenReturn(sleepDetail1);

        // Run the test
        final SleepDetail result = sleepDetailServiceImplUnderTest.saveSleepDetail(sleepDetail);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
