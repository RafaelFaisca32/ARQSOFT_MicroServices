package gorgeousSandwich.shop.Shared.domain.patterns;

public interface IEntity<T extends IEntityId> {
    boolean sameAs(IEntity<? extends IEntityId> otherEntity);

    T obtainId();
}
