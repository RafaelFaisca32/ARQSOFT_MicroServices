package gorgeousSandwich.order.Domain;

import gorgeousSandwich.order.Shared.domain.patterns.EntityId;

public class OrderId extends EntityId {

    public OrderId() {
        super();
    }

    public OrderId(Long id) {
        super(id);
    }
}
