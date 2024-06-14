package gorgeousSandwich.promotion.Domain;


import gorgeousSandwich.promotion.Shared.domain.valueobjects.Percentage;
import gorgeousSandwich.promotion.Shared.domain.valueobjects.TimeOfEffect;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public abstract class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Embedded
    private TimeOfEffect timeOfEffect;
    @Embedded
    private Percentage percentage;
    @Enumerated
    private PromotionType type;
    Promotion(TimeOfEffect timeOfEffect, Percentage percentage, PromotionType type) {
        this.timeOfEffect = timeOfEffect;
        this.percentage = percentage;
        this.type=type;
    }

    Promotion(Long id, TimeOfEffect timeOfEffect, Percentage percentage, PromotionType type) {
        this.id = id;
        this.timeOfEffect = timeOfEffect;
        this.percentage = percentage;
        this.type=type;
    }

}
