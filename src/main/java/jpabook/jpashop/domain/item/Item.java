package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    // 비즈니스 로직 //

    /**
     * 재고 수량 증가
     */
    public void addStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 수량 감소
     */
    public void removeStockQuantity(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
