package com.alive_backend.serviceimpl.health_data;

import com.alive_backend.dao.health_data.DietDao;
import com.alive_backend.entity.health_data.Diet;
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
class DietServiceImplTest {

    @Mock
    private DietDao mockDietDao;

    @InjectMocks
    private DietServiceImpl dietServiceImplUnderTest;

    @Test
    void testAddDiet() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet.setType(0);

        final Diet expectedResult = new Diet();
        expectedResult.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        expectedResult.setAmount(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        expectedResult.setType(0);

        // Configure DietDao.addDiet(...).
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet1.setType(0);
        final Diet diet2 = new Diet();
        diet2.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet2.setAmount(0.0);
        diet2.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet2.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet2.setType(0);
        when(mockDietDao.addDiet(diet2)).thenReturn(diet1);

        // Run the test
        final Diet result = dietServiceImplUnderTest.addDiet(diet);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindDietByUserIdAndFoodIdAndDateAndType() {
        // Setup
        final Diet expectedResult = new Diet();
        expectedResult.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        expectedResult.setAmount(0.0);
        expectedResult.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        expectedResult.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        expectedResult.setType(0);

        // Configure DietDao.findDietByUserIdAndFoodIdAndDateAndType(...).
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet.setType(0);
        when(mockDietDao.findDietByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("33c48ae1-a85a-4fd6-8280-8f980be9ac00"), Date.valueOf(LocalDate.of(2020, 1, 1)),
                0)).thenReturn(diet);

        // Run the test
        final Diet result = dietServiceImplUnderTest.findDietByUserIdAndFoodIdAndDateAndType(0,
                UUID.fromString("33c48ae1-a85a-4fd6-8280-8f980be9ac00"), Date.valueOf(LocalDate.of(2020, 1, 1)), 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateDiet() {
        // Setup
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet1.setType(0);

        // Run the test
        dietServiceImplUnderTest.updateDiet(diet1);

        // Verify the results
        // Confirm DietDao.updateDiet(...).
        final Diet diet11 = new Diet();
        diet11.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet11.setAmount(0.0);
        diet11.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet11.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet11.setType(0);
        verify(mockDietDao).updateDiet(diet11);
    }

    @Test
    void testFindDietByUserIdAndDate() {
        // Setup
        final Diet diet = new Diet();
        diet.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet.setAmount(0.0);
        diet.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet.setType(0);
        final List<Diet> expectedResult = Arrays.asList(diet);

        // Configure DietDao.findDietByUserIdAndDate(...).
        final Diet diet1 = new Diet();
        diet1.setId(UUID.fromString("3b94c5e4-708f-4954-b1ac-ecd12ab7cc0f"));
        diet1.setAmount(0.0);
        diet1.setDate(Date.valueOf(LocalDate.of(2020, 1, 1)));
        diet1.setFoodId(UUID.fromString("f826627f-78e0-4540-a393-8c9f07b0b3c0"));
        diet1.setType(0);
        final List<Diet> diets = Arrays.asList(diet1);
        when(mockDietDao.findDietByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1)))).thenReturn(diets);

        // Run the test
        final List<Diet> result = dietServiceImplUnderTest.findDietByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindDietByUserIdAndDate_DietDaoReturnsNoItems() {
        // Setup
        when(mockDietDao.findDietByUserIdAndDate(0, Date.valueOf(LocalDate.of(2020, 1, 1))))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Diet> result = dietServiceImplUnderTest.findDietByUserIdAndDate(0,
                Date.valueOf(LocalDate.of(2020, 1, 1)));

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
