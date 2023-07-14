package com.alive_backend.repository.health_data;

import com.alive_backend.entity.health_data.Weight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class WeightRepositoryTest {
    private WeightRepository weightRepository;
    private Weight fakeWeight1, fakeWeight2;

    @BeforeEach
    void setUp() {
        fakeWeight1 = new Weight(); fakeWeight2 = new Weight();
        fakeWeight1.setUserId(1); fakeWeight1.setDate(Date.valueOf("2022-07-10")); fakeWeight1.setWeight(60.0);
        fakeWeight2.setUserId(1); fakeWeight2.setDate(Date.valueOf("2022-07-11")); fakeWeight2.setWeight(61.0);
        // 创建一个匿名的子类来测试抽象类行为
        weightRepository = new WeightRepository () {
            @Override
            public List<Weight> findAll() {
                return null;
            }

            @Override
            public List<Weight> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Weight> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public List<Weight> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Weight entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends Weight> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Weight> S save(S entity) {
                return null;
            }

            @Override
            public <S extends Weight> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<Weight> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Weight> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Weight> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Weight> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Weight getOne(Integer integer) {
                return null;
            }

            @Override
            public Weight getById(Integer integer) {
                return null;
            }

            @Override
            public <S extends Weight> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Weight> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Weight> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends Weight> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Weight> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Weight> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Weight, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public Weight findByUserIdAndDate(int id, Date date) {
                return fakeWeight1;
            }

            @Override
            public java.util.List<Weight> findByUserId(int id) {
                return java.util.Arrays.asList(fakeWeight1, fakeWeight2);
            }

            @Override
            public Weight findTopByUserIdOrderByDateDesc(int id) {
                return fakeWeight1;
            }

            @Override
            public Weight findTopByUserIdAndDateBeforeOrderByDateDesc(int id, Date date) {
                return fakeWeight1;
            }
        };
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUserIdAndDate() {
        Weight weight = weightRepository.findByUserIdAndDate(1, Date.valueOf("2022-07-10"));
        assertEquals(fakeWeight1, weight);
    }

    @Test
    void findByUserId() {
        List<Weight> weights = weightRepository.findByUserId(1);
        assertEquals(java.util.Arrays.asList(fakeWeight1, fakeWeight2), weights);
    }

    @Test
    void findTopByUserIdOrderByDateDesc() {
        Weight weight = weightRepository.findTopByUserIdOrderByDateDesc(1);
        assertEquals(fakeWeight1, weight);
    }

    @Test
    void findTopByUserIdAndDateBeforeOrderByDateDesc() {
        Weight weight = weightRepository.findTopByUserIdAndDateBeforeOrderByDateDesc(1, Date.valueOf("2022-07-11"));
        assertEquals(fakeWeight1, weight);
    }
}