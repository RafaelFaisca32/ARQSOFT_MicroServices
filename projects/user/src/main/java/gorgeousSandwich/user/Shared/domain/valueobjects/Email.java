package gorgeousSandwich.user.Shared.domain.valueobjects;

import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jboss.logging.BasicLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);
    private String email;

    public Email(String email) throws BusinessRuleViolationException {
        if(isValid(email)){
            this.email = email;
        } else {
            throw new BusinessRuleViolationException("Invalid email");
        }
    }

    public boolean isValid(String email) {
        if(email!=null && !email.isEmpty() && email.contains("@")){
            return true;
        } else {
            return false;
        }
    }

    public static Email of(String email) throws BusinessRuleViolationException {
        try {
            return new Email(email);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("String %s violates Email's rules!", email),e);
            throw e;
        }
    }
}
