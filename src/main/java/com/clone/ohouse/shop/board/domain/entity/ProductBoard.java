package com.clone.ohouse.shop.board.domain.entity;

import com.clone.ohouse.shop.BaseTimeEntity;
import com.clone.ohouse.shop.product.domain.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ProductBoard extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive = false;

    @Column(length = 200)
    private String title = "제목 없음";

    @Lob
    private byte[] content;

    @Column(length = 45, nullable = false)
    private String author;

    @Column(length = 45)
    private String modifiedUser;

    private boolean is_delete = false;
    private Integer hit = 0;

    @OneToMany(mappedBy = "productBoard")
    private List<Product> productList = new ArrayList<>();

    public ProductBoard(String title, byte[] content, String author, String modifiedUser) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.modifiedUser = modifiedUser;
    }

}
