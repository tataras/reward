package com.tatara.reward.product.repository;

import com.tatara.reward.product.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserId(Long userId);

    @Query(value = "from order_history o where user_id = :userId AND date BETWEEN :startDate AND :endDate")
    List<OrderEntity> getAllBetweenDates(Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
