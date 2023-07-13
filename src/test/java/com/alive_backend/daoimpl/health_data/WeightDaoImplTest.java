package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.repository.health_data.WeightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeightDaoImplTest {
    @Mock
    private WeightRepository weightRepository;
    @InjectMocks
    private WeightDaoImpl weightDao;

    private Weight fakeWeight1, fakeWeight2;

    @BeforeEach
    void setUp() {
        fakeWeight1 = new Weight(); fakeWeight2 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(java.sql.Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(java.sql.Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void addWeight() {
        when(weightRepository.save(fakeWeight1)).thenReturn(fakeWeight1);

        Weight weight = weightDao.addWeight(fakeWeight1);
        assertEquals(weight, fakeWeight1);
        verify(weightRepository, times(1)).save(fakeWeight1);
    }

    @Test
    void getWeightByDate() {
        when(weightRepository.findByUserIdAndDate(1, java.sql.Date.valueOf("2022-07-10"))).thenReturn(fakeWeight1);
        Weight weight = weightDao.getWeightByDate(1, java.sql.Date.valueOf("2022-07-10"));
        assertEquals(weight, fakeWeight1);
        verify(weightRepository, times(1)).findByUserIdAndDate(1, java.sql.Date.valueOf("2022-07-10"));
    }

    @Test
    void getWeightByUser() {
        when(weightRepository.findByUserId(1)).thenReturn(Arrays.asList(fakeWeight1, fakeWeight2));
        assertEquals(weightDao.getWeightByUser(1), Arrays.asList(fakeWeight1, fakeWeight2));
        verify(weightRepository, times(1)).findByUserId(1);
    }

    @Test
    void getLatestWeight() {
        when(weightRepository.findTopByUserIdOrderByDateDesc(1)).thenReturn(fakeWeight1);
        assertEquals(weightDao.getLatestWeight(1), fakeWeight1);
        verify(weightRepository, times(1)).findTopByUserIdOrderByDateDesc(1);
    }

    @Test
    void getWeightBeforeDate() {
        when(weightRepository.findTopByUserIdAndDateBeforeOrderByDateDesc(1, java.sql.Date.valueOf("2022-07-11"))).thenReturn(fakeWeight1);
        assertEquals(weightDao.getWeightBeforeDate(1, java.sql.Date.valueOf("2022-07-11")), fakeWeight1);
        verify(weightRepository, times(1)).findTopByUserIdAndDateBeforeOrderByDateDesc(1, java.sql.Date.valueOf("2022-07-11"));
    }
}