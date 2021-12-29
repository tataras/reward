package com.tatara.reward.product.service;

import com.tatara.reward.exceptions.NotEnoughQuantityException;
import com.tatara.reward.product.model.OrderEntity;
import com.tatara.reward.product.model.ProductEntity;
import com.tatara.reward.product.api.OrderProductRequest;
import com.tatara.reward.product.repository.OrderRepository;
import com.tatara.reward.user.UserEntity;
import com.tatara.reward.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;

    @Transactional
    public void process(OrderProductRequest request, Long userId) {
        UserEntity user = userService.findById(userId);
        ProductEntity product = productService.findById(request.getProductId());
        checkProductAvailability(product, request);

        OrderEntity order = OrderEntity.builder()
                .product(product)
                .user(user)
                .quantity(request.getQuantity())
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                .date(LocalDateTime.now())
                .build();

        orderRepository.save(order);
        product.subtractQuantity(request.getQuantity());
        productService.save(product);
    }

    public List<OrderEntity> getUserOrders(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }
    public List<OrderEntity> getUserOrders(Long userId, int month, int year) {
        return orderRepository.getAllBetweenDates(userId, getStartDate(month, year), getEndDate(month, year));
    }

    private void checkProductAvailability(ProductEntity product, OrderProductRequest request) {
        if(product.getAvailableQuantity() - request.getQuantity() < 0) {
            throw new NotEnoughQuantityException("Number of available products: " + product.getAvailableQuantity());
        }
    }

    private LocalDateTime getStartDate(int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        return LocalDateTime.of(startDate, LocalTime.MIN);
    }

    private LocalDateTime getEndDate(int month, int year) {
        LocalDate endDate = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        return LocalDateTime.of(endDate, LocalTime.MAX);
    }
}
