package com.tatara.reward.reward;

import com.tatara.reward.IntegrationBaseTest;
import com.tatara.reward.product.model.OrderEntity;
import com.tatara.reward.product.model.ProductEntity;
import com.tatara.reward.product.repository.OrderRepository;
import com.tatara.reward.product.repository.ProductRepository;
import com.tatara.reward.user.UserEntity;
import com.tatara.reward.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RewardControllerTest extends IntegrationBaseTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private UserEntity user;

    @AfterEach
    public void cleanOrders() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @BeforeEach
    public void saveTestData() {
        user = userRepository.save(new UserEntity(null, "Aaa", "Bbbb"));
        ProductEntity product1 = productRepository.save(new ProductEntity(null, "Product 1", 68, BigDecimal.valueOf(10.99)));
        ProductEntity product2 = productRepository.save(new ProductEntity(null, "Product 2", 12, BigDecimal.valueOf(99.99)));
        ProductEntity product3 = productRepository.save(new ProductEntity(null, "Product 3", 47, BigDecimal.valueOf(50.00)));
        orderRepository.save(new OrderEntity(null, user, product1, 10, BigDecimal.valueOf(109.9), LocalDateTime.of(2021, 10, 10, 12, 0)));
        orderRepository.save(new OrderEntity(null, user, product2, 8, BigDecimal.valueOf(799.2), LocalDateTime.of(2021, 11, 18, 12, 0)));
        orderRepository.save(new OrderEntity(null, user, product3, 4, BigDecimal.valueOf(200), LocalDateTime.of(2021, 10, 17, 12, 0)));
    }

    @Test
    public void shouldReturnPointsForGivenMonth() throws URISyntaxException {
        // given
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/reward/" + user.getId() + "/10/2021");

        // when
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        // then
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("{\"points\":318}", result.getBody());
    }

    @Test
    public void shouldReturnAllUserPoints() throws URISyntaxException {
        // given
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/reward/" + user.getId());

        // when
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        // then
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("{\"points\":1766}", result.getBody());
    }

}
