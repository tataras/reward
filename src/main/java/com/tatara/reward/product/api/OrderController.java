package com.tatara.reward.product.api;

import com.tatara.reward.product.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> buyProducts(@PathVariable Long userId, @Valid @RequestBody OrderProductRequest orderProductRequest) {
        orderService.process(orderProductRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
