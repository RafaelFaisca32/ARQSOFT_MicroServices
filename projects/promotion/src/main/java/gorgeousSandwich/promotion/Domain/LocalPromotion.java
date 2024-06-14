package gorgeousSandwich.promotion.Domain;

import gorgeousSandwich.promotion.Shared.domain.valueobjects.Percentage;
import gorgeousSandwich.promotion.Shared.domain.valueobjects.TimeOfEffect;
import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.promotion.Shared.exceptions.ValidationException;
import gorgeousSandwich.promotion.Util.Validations;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.TypeAlias;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LocalPromotion extends Promotion {

    private Long shopId;

    LocalPromotion(TimeOfEffect timeOfEffect, Percentage percentage, Long shop) throws BusinessRuleViolationException {
        super(timeOfEffect, percentage, PromotionType.LOCAL);
        try {
            Validations.notNull(shop);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException("Shop id is null!", e);
        }
        Validations.notNull(shop);
        this.shopId = shop;
    }

    LocalPromotion(Long id, TimeOfEffect timeOfEffect, Percentage percentage, Long shop) throws BusinessRuleViolationException {
        super(id, timeOfEffect, percentage, PromotionType.LOCAL);
        try {
            Validations.notNull(shop);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException("Shop id is null!", e);
        }
        Validations.notNull(id);
        this.shopId = shop;
    }


}
