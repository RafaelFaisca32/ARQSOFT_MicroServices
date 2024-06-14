package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.valueobjects.Percentage;
import gorgeousSandwich.promotion.Shared.domain.valueobjects.TimeOfEffect;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.TypeAlias;

import java.lang.annotation.Inherited;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class GlobalPromotion extends Promotion {

    GlobalPromotion(TimeOfEffect timeOfEffect, Percentage percentage) {
        super(timeOfEffect, percentage, PromotionType.GLOBAL);
    }


    GlobalPromotion(Long id, TimeOfEffect timeOfEffect, Percentage percentage) {
        super(id, timeOfEffect, percentage, PromotionType.GLOBAL);
    }
}

