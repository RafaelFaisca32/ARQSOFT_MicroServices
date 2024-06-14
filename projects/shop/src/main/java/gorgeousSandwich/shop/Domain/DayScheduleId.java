package gorgeousSandwich.shop.Domain;


import gorgeousSandwich.shop.Shared.domain.patterns.EntityId;
import gorgeousSandwich.shop.Shared.domain.patterns.IEntityId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DayScheduleId implements IEntityId {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long id() {
        return id;
    }
}
