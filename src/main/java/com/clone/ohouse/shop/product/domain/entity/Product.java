package com.clone.ohouse.shop.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;

    @ManyToOne
    @JoinColumn(name = "item_seq")
    private Item item;

    @Column(length = 45)
    private String productName;
    private Integer price;
    private Integer stock;
    private Integer rateDiscount;

    @Column(length = 30)
    private String size;
    @Column(length = 30)
    private String color;

    private Character optionalYn;

    @Builder
    public Product(Item item, String productName, Integer price, Integer stock, Integer rateDiscount, String size, String color, Character optionalYn) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
        this.optionalYn = optionalYn;
    }


    public void update(Item item, String productName, Integer stock, Integer price, Integer rateDiscount, String size, String color, Character optional_yn) {
        this.item = item;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.rateDiscount = rateDiscount;
        this.size = size;
        this.color = color;
        this.optionalYn = optional_yn;
    }

}
