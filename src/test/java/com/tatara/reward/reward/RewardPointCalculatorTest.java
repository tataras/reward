package com.tatara.reward.reward;

import com.tatara.reward.product.model.OrderEntity;
import com.tatara.reward.reward.service.RewardPointsCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardPointCalculatorTest {

    public static Object[][] testData() {
        return new Object[][]{
                { 0, Collections.emptyList() },
                { 90, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(120)).build()) },
                { 0, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(50.99)).build()) },
                { 1, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(51)).build()) },
                { 49, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(99.99)).build()) },
                { 50, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(100.99)).build()) },
                { 0, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(50)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(40)).build()) },
                { 100, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(120)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(60)).build()) },
                { 140, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(120)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(50)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(100.99)).build()) },
                { 162, List.of(OrderEntity.builder().totalPrice(BigDecimal.valueOf(120)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(72)).build(), OrderEntity.builder().totalPrice(BigDecimal.valueOf(100.99)).build()) },

        };
    }

    @ParameterizedTest
    @MethodSource(value =  "testData")
    public void shouldCalculatePoints(Integer expectedPoints, List<OrderEntity> orders) {
        // given
        RewardPointsCalculator rewardPointsCalculator = new RewardPointsCalculator();

        // when
        Integer calculatedPoints = rewardPointsCalculator.calculatePoints(orders);

        // then
        assertEquals(expectedPoints, calculatedPoints);
    }
}
