package gorgeousSandwich.promotion.Shared.domain.valueobjects;

import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.promotion.Shared.exceptions.ValidationException;
import gorgeousSandwich.promotion.Util.Validations;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalPrice {

    private double totalValue;

    public TotalPrice(double totalValue) throws BusinessRuleViolationException {
        try {
            Validations.notNull(totalValue);
            Validations.numberIsPositive(totalValue);
        } catch (ValidationException e) {
            throw new BusinessRuleViolationException(String.format("TotalPrice " + totalValue  + " cannot be neither null nor less than 0"));
        }
        this.totalValue = totalValue;
    }

//    public TotalPrice(){
//        this.totalValue = 1;
//    }

    public double getTotalValue() {
        return totalValue;
    }
}
