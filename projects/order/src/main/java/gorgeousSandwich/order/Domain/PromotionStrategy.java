package gorgeousSandwich.order.Domain;

public abstract class PromotionStrategy {

    private PromotionStrategyType type;

    public PromotionStrategy(PromotionStrategyType type) {
        this.type = type;
    }

    public PromotionStrategyType getType() {
        return type;
    }
}
