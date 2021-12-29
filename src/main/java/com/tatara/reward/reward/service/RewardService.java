package com.tatara.reward.reward.service;

import com.tatara.reward.product.model.OrderEntity;
import com.tatara.reward.product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RewardService {

    private final OrderService orderService;
    private final RewardPointsCalculator rewardPointsCalculator;

    public Integer calculatePoints(Long userId) {
        List<OrderEntity> userOrders = orderService.getUserOrders(userId);
        return rewardPointsCalculator.calculatePoints(userOrders);
    }

    public Integer calculatePoints(Long userId, Integer month, Integer year) {
        List<OrderEntity> userOrders = orderService.getUserOrders(userId, month, year);
        return rewardPointsCalculator.calculatePoints(userOrders);
    }
}
