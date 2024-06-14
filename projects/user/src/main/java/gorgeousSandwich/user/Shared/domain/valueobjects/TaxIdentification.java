package gorgeousSandwich.user.Shared.domain.valueobjects;

import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class TaxIdentification {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxIdentification.class);
    private String taxIdentification;

    public TaxIdentification(String taxIdentification) throws BusinessRuleViolationException {
        if(isValid(taxIdentification)) {
            this.taxIdentification = taxIdentification;
        } else {
            throw new BusinessRuleViolationException("Invalid taxIdentification");
        }
    }

    public String getTaxIdentification() {
        return taxIdentification;
    }

    public boolean isValid(String taxIdentification) {
        if(taxIdentification!=null && !taxIdentification.isEmpty() && taxIdentification.length()==9){
            return true;
        } else {
            return false;
        }
    }

    public static TaxIdentification of(String taxIdentification) throws BusinessRuleViolationException {
        try {
            return new TaxIdentification(taxIdentification);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("String %s violates Tax Identification's rules!", taxIdentification),e);
            throw e;
        }
    }
}
