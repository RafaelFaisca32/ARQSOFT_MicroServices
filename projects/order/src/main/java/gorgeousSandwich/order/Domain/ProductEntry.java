package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.domain.valueobjects.Quantity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@AllArgsConstructor
public class ProductEntry {

    private Long sandwichPrice;

    private Quantity quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ProductEntry(Long sandwichId, int quantity) {
        this.sandwichPrice = sandwichId;
        this.quantity = new Quantity(quantity);
    }
}
