package com.tatara.reward.reward.service;

import com.tatara.reward.product.model.OrderEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RewardPointsCalculator {

    public Integer calculatePoints(List<OrderEntity> userOrders) {
        return userOrders.stream()
                .map(this::calculatePoints)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private Integer calculatePoints(OrderEntity order) {
        int points;
        if(order.getTotalPrice().compareTo(BigDecimal.valueOf(100)) > 0) {
            int xd = order.getTotalPrice().subtract(BigDecimal.valueOf(100)).toBigInteger().intValue();
            points = (xd * 2) + 50;
        } else if(order.getTotalPrice().compareTo(BigDecimal.valueOf(50)) < 0) {
            points = 0;
        } else {
            points = order.getTotalPrice().subtract(BigDecimal.valueOf(50)).toBigInteger().intValue();
        }
        return points;
    }
}
