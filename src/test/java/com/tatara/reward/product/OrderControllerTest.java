package com.tatara.reward.product;

import com.tatara.reward.IntegrationBaseTest;
import com.tatara.reward.product.api.OrderProductRequest;
import com.tatara.reward.product.model.OrderEntity;
import com.tatara.reward.product.model.ProductEntity;
import com.tatara.reward.product.repository.OrderRepository;
import com.tatara.reward.product.repository.ProductRepository;
import com.tatara.reward.user.UserEntity;
import com.tatara.reward.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderControllerTest extends IntegrationBaseTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void cleanOrders() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void shouldSaveNewOrder() throws URISyntaxException {
        // given
        UserEntity user = userRepository.save(new UserEntity(null, "Aaa", "Bbbb"));
        ProductEntity product = productRepository.save(new ProductEntity(null, "Product", 68, BigDecimal.valueOf(10.99)));
        OrderProductRequest orderProductRequest = new OrderProductRequest(product.getId(), 10);
        HttpEntity<OrderProductRequest> request = new HttpEntity<>(orderProductRequest, new HttpHeaders());
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/order/" + user.getId());

        // when
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        List<OrderEntity> orders = orderRepository.findAll();
        List<ProductEntity> products = productRepository.findAll();

        // then
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(1, orders.size());
        assertEquals(Integer.valueOf(10), orders.get(0).getQuantity());
        assertEquals(Long.valueOf(2), orders.get(0).getProduct().getId());
        assertEquals(Long.valueOf(1), orders.get(0).getUser().getId());
        assertEquals(Integer.valueOf(58), products.get(0).getAvailableQuantity());
    }

    @Test
    public void shouldReturnBadRequestBecauseOfTooLowProductQuantity() throws URISyntaxException {
        // given
        UserEntity user = userRepository.save(new UserEntity(null, "Aaa", "Bbbb"));
        ProductEntity product = productRepository.save(new ProductEntity(null, "Product", 2, BigDecimal.valueOf(10.99)));
        OrderProductRequest orderProductRequest = new OrderProductRequest(product.getId(), 10);
        HttpEntity<OrderProductRequest> request = new HttpEntity<>(orderProductRequest, new HttpHeaders());
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/order/" + user.getId());

        // when
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        List<OrderEntity> orders = orderRepository.findAll();

        // then
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Number of available products: 2", result.getBody());
        assertTrue(orders.isEmpty());
    }
}
