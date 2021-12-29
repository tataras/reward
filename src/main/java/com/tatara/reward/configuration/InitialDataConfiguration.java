package com.tatara.reward.configuration;

import com.tatara.reward.product.model.ProductEntity;
import com.tatara.reward.product.service.ProductService;
import com.tatara.reward.user.UserEntity;
import com.tatara.reward.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;


@Configuration
@Profile("!integration-tests")
public class InitialDataConfiguration {

    Logger logger = LoggerFactory.getLogger(InitialDataConfiguration.class);

    @Bean
    public CommandLineRunner loadTestData(UserService userService, ProductService productService) {
        return (args) -> {
            userService.save(new UserEntity(1L, "Aaa", "Aaa"));
            userService.save(new UserEntity(2L, "Bbb", "Bbb"));
            userService.save(new UserEntity(3L, "Ccc", "Ccc"));

            productService.save(new ProductEntity(4L, "First product", 76, BigDecimal.valueOf(9.99)));
            productService.save(new ProductEntity(5L, "Second product", 0, BigDecimal.valueOf(11.99)));
            productService.save(new ProductEntity(6L, "Third product", 18, BigDecimal.valueOf(18.79)));
            logger.info("Database filled with initial data");
        };
    }


}
