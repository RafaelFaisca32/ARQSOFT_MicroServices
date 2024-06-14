package gorgeousSandwich.order.Shared.domain.valueobjects;

import gorgeousSandwich.order.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.order.Shared.exceptions.ValidationException;
import gorgeousSandwich.order.Util.Validations;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Promotion {

    private int promotion;

    public Promotion(int promotion) throws BusinessRuleViolationException {
        try {
            Validations.notNull(promotion);
            Validations.numberIsPositive(promotion);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException(String.format("TotalPrice " + promotion + " cannot be neither null nor less than 0"));
        }
        this.promotion = promotion;
    }
}
