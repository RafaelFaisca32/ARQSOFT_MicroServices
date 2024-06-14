package gorgeousSandwich.order.Shared.domain.valueobjects;

import gorgeousSandwich.order.Shared.domain.patterns.IEntityId;

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
