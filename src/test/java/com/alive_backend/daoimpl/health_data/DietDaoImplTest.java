package com.alive_backend.daoimpl.health_data;

import com.alive_backend.entity.health_data.Diet;
import com.alive_backend.repository.health_data.DietRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DietDaoImplTest {

    @Mock
    private DietRepository mockDietRepository;

    @InjectMocks
    private DietDaoImpl dietDaoImplUnderTest;

    @Test
    void testGetDietByUserId() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet.setType(0);
        final List<Diet> expectedResult = Arrays.asList(diet);

        // Configure DietRepository.getByUserId(...).
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet1.setType(0);
        final List<Diet> diets = Arrays.asList(diet1);
        when(mockDietRepository.getByUserId(0)).thenReturn(diets);

        // Run the test
        final List<Diet> result = dietDaoImplUnderTest.getDietByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDietByUserId_DietRepositoryReturnsNoItems() {
        // Setup
        when(mockDietRepository.getByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Diet> result = dietDaoImplUnderTest.getDietByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testAddDiet() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet.setType(0);

        final Diet expectedResult = new Diet();
        expectedResult.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        expectedResult.setAmount(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        expectedResult.setType(0);

        // Configure DietRepository.save(...).
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet1.setType(0);
        final Diet entity = new Diet();
        entity.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        entity.setAmount(0.0);
        entity.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        entity.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        entity.setType(0);
        when(mockDietRepository.save(entity)).thenReturn(diet1);

        // Run the test
        final Diet result = dietDaoImplUnderTest.addDiet(diet);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteDiet() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet.setType(0);

        // Run the test
        dietDaoImplUnderTest.deleteDiet(diet);

        // Verify the results
        // Confirm DietRepository.delete(...).
        final Diet entity = new Diet();
        entity.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        entity.setAmount(0.0);
        entity.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        entity.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        entity.setType(0);
        verify(mockDietRepository).delete(entity);
    }

    @Test
    void testFindDietByUserIdAndFoodIdAndDateAndType() {
        // Setup
        final Diet expectedResult = new Diet();
        expectedResult.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        expectedResult.setAmount(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        expectedResult.setType(0);

        // Configure DietRepository.getByUserIdAndFoodIdAndDateAndType(...).
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet.setType(0);
        when(mockDietRepository.getByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("230c4c66-7dde-4212-b701-1837f6ee4369"), Date.valueOf(LocalDate.of(2020, 1, 1)),
                0)).thenReturn(diet);

        // Run the test
        final Diet result = dietDaoImplUnderTest.findDietByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("230c4c66-7dde-4212-b701-1837f6ee4369"), Date.valueOf(LocalDate.of(2020, 1, 1)), 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateDiet() {
        // Setup
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet1.setType(0);

        // Run the test
        dietDaoImplUnderTest.updateDiet(diet1);

        // Verify the results
        // Confirm DietRepository.save(...).
        final Diet entity = new Diet();
        entity.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        entity.setAmount(0.0);
        entity.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        entity.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        entity.setType(0);
        verify(mockDietRepository).save(entity);
    }

    @Test
    void testFindDietByUserIdAndDate() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet.setType(0);
        final List<Diet> expectedResult = Arrays.asList(diet);

        // Configure DietRepository.getByUserIdAndDate(...).
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("72964054-47f5-4463-a73b-b4499bc56b78"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("c651720b-82fd-475c-adbc-98714fde64bc"));
        diet1.setType(0);
        final List<Diet> diets = Arrays.asList(diet1);
        when(mockDietRepository.getByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(diets);

        // Run the test
        final List<Diet> result = dietDaoImplUnderTest.findDietByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindDietByUserIdAndDate_DietRepositoryReturnsNoItems() {
        // Setup
        when(mockDietRepository.getByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Diet> result = dietDaoImplUnderTest.findDietByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
