package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.domain.valueobjects.Promotion;
import gorgeousSandwich.order.Shared.domain.valueobjects.TotalPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "systemorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long shopid;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductEntry> productEntries;

    @Embedded
    private Promotion promotion;

    @Embedded
    private TotalPrice price;


    protected Order(Long id, Long shop, List<ProductEntry> productEntries, Promotion promotion, TotalPrice totalPrice) {
        this.id = id;
        this.shopid = shop;
        this.productEntries = productEntries;
        this.promotion = promotion;
        this.price = totalPrice;
    }
}
