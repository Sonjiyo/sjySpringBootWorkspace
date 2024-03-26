package com.basic.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId; // 상품 id
    private Long quantity; // 수량

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity(){
        return quantity;
    }

    // 재고 감소 로직
    public void decreaseStock(Long quantity){
        if(this.quantity - quantity < 0){
            throw new RuntimeException("재고 수량은 0보다 작을 수 없습니다");
        }
        this.quantity -= quantity;
    }


}
