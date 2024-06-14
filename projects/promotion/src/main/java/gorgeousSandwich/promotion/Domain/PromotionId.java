package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.patterns.EntityId;
import gorgeousSandwich.promotion.Shared.domain.patterns.IEntityId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PromotionId implements IEntityId {
    @Column(name = "promotionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PromotionId(Long id) {
        this.id = id;
    }

    @Override
    public Long id() {
        return id;
    }
}
