package gorgeousSandwich.order.Shared.domain.valueobjects;

import gorgeousSandwich.order.Shared.domain.patterns.IValueObject;

public class Quantity implements IValueObject {

    private final int value;

    public Quantity(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
