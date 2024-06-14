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
public final class Username {

    private static final Logger LOGGER = LoggerFactory.getLogger(Username.class);
    private String username;

    public Username(String username) throws BusinessRuleViolationException {
        if(isValid(username)){
            this.username = username;
        } else {
            throw new BusinessRuleViolationException("Invalid username");
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid(String username) {
        if(username!=null && !username.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public static Username of(String username) throws BusinessRuleViolationException {
        try {
            return new Username(username);
        } catch (BusinessRuleViolationException e) {
            LOGGER.error(String.format("String %s violates Username's rules!", username),e);
            throw e;
        }
    }
}
