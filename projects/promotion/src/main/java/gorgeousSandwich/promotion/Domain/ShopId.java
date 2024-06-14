package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.patterns.IEntityId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShopId implements IEntityId {
    @Column(name = "shopId")
    private Long id;

    public ShopId() {

    }

    public ShopId(Long id) {
        this.id = id;
    }

    @Override
    public Long id() {
        return id;
    }
}
