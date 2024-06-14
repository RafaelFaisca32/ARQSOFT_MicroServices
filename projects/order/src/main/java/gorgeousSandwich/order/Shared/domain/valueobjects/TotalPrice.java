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
public class TotalPrice {

    private double totalValue;

    public TotalPrice(double totalValue) throws BusinessRuleViolationException {
        try {
            Validations.notNull(totalValue);
            Validations.numberIsPositive(totalValue);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException(String.format("TotalPrice " + totalValue + " cannot be neither null nor less than 0"));
        }
        this.totalValue = totalValue;
    }
}
