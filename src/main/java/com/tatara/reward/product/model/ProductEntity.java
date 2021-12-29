package com.tatara.reward.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer availableQuantity;
    private BigDecimal price;

    public void subtractQuantity(Integer quantity) {
        this.availableQuantity -= quantity;
    }

}
