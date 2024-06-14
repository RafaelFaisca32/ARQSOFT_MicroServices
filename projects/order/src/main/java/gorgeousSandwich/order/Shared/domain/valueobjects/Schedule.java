package gorgeousSandwich.order.Shared.domain.valueobjects;

import gorgeousSandwich.order.Shared.domain.patterns.IValueObject;
import gorgeousSandwich.order.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.order.Shared.exceptions.ValidationException;
import gorgeousSandwich.order.Util.Validations;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Schedule implements IValueObject {
    private int openingHour;
    private int closingHour;
    private static final Logger LOGGER = LoggerFactory.getLogger(Schedule.class);

    public static Schedule of(int openingHour, int closingHour) throws BusinessRuleViolationException {
        try {
            return new Schedule(openingHour, closingHour);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("Integers %d and %d violate Schedule's rules!", openingHour, closingHour), e);
            throw e;
        }
    }


    private Schedule(int openingHour, int closingHour) throws BusinessRuleViolationException {
        try {
            Validations.numberIsBetween(openingHour, 0, 24);
        } catch (ValidationException exception) {
            throw new BusinessRuleViolationException(String.format("Opening hour must be between 0 and 24 and not %d", openingHour));
        }
        this.openingHour = openingHour;
        try {
            Validations.numberIsBetween(closingHour, 0, 24);
        } catch (ValidationException exception) {
            throw new BusinessRuleViolationException(String.format("Closing hour must be between 0 and 24 and not %d", closingHour));
        }
        this.closingHour = closingHour;
    }


}
