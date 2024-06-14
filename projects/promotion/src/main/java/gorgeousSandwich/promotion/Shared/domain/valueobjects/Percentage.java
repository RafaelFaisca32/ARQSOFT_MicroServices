package gorgeousSandwich.promotion.Shared.domain.valueobjects;


import gorgeousSandwich.promotion.Shared.domain.patterns.IValueObject;
import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.promotion.Shared.exceptions.ValidationException;
import gorgeousSandwich.promotion.Util.Validations;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Percentage implements IValueObject {
    private double percentage;

    private Percentage(double percentage) throws BusinessRuleViolationException {
        try {
            Validations.numberIsInBetween(percentage,0.0,100.0);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException("Could not create percentage for promotion!",e);
        }
        this.percentage = percentage;
    }


    public static Percentage of(double percentage) throws BusinessRuleViolationException {
        return new Percentage(percentage);
    }

    public double getPercentage() {
        return percentage;
    }
}
