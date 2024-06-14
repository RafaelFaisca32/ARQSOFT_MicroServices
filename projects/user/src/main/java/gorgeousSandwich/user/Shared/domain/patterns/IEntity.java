package gorgeousSandwich.user.Shared.domain.patterns;

public interface IEntity<T extends IEntityId> {
    boolean sameAs(IEntity<? extends IEntityId> otherEntity);

    T obtainId();
}
