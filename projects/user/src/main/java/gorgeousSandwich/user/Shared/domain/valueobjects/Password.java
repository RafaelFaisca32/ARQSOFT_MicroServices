package gorgeousSandwich.user.Shared.domain.valueobjects;

import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.function.IntPredicate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Password {

    private static final Logger LOGGER = LoggerFactory.getLogger(Password.class);
    private String password;

    public Password(String password) throws BusinessRuleViolationException, NoSuchAlgorithmException {
        if(isValid(password)){
            this.password = password;
        }
        else {
            throw new BusinessRuleViolationException("Invalid password, it need to be at least " +
                    "8 characters long, with an Upper Case letter and contain a number");
        }
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid(String password) {
        char uppercase;
        if(password!=null && !password.isEmpty() && password.length() >= 8
                && containsUpperCase(password) && containsNumber(password)){
            return true;
        } else {
            return false;
        }
    }

    private boolean containsUpperCase(String value) {
        return contains(value, i -> Character.isLetter(i) && Character.isUpperCase(i));
    }

    private boolean containsNumber(String value) {

        return contains(value, Character::isDigit);
    }

    private boolean contains(String value, IntPredicate predicate) {

        return value.chars().anyMatch(predicate);
    }

    public static Password of(String password) throws BusinessRuleViolationException, NoSuchAlgorithmException {
        try {
            return new Password(password);
        } catch (BusinessRuleViolationException | NoSuchAlgorithmException e) {
            LOGGER.error(String.format("String %s violates Passoword's rules!", password),e);
            throw e;
        }
    }
}
