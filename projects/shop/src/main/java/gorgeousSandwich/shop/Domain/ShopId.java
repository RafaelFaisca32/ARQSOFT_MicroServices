package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Shared.domain.patterns.EntityId;
import gorgeousSandwich.shop.Shared.domain.patterns.IEntityId;

public class ShopId implements IEntityId {
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
