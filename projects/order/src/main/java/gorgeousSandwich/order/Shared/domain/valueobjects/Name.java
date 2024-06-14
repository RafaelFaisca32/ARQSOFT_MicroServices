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
public final class Name implements IValueObject {

    private String stringValue;
    private static final Logger LOGGER = LoggerFactory.getLogger(Name.class);


    private Name(String stringValue) throws BusinessRuleViolationException {
        try {
            Validations.notNull(stringValue);
            Validations.notEmpty(stringValue);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException(String.format("Name '%s' cannot be neither null nor empty", stringValue));
        }
        this.stringValue = stringValue;
    }

    public static Name of(String name) throws BusinessRuleViolationException {
        try {
            return new Name(name);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("String %s violates Name's rules!", name), e);
            throw e;
        }
    }
}
