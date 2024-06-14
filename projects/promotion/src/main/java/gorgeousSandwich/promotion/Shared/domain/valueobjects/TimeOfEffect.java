package gorgeousSandwich.promotion.Shared.domain.valueobjects;


import gorgeousSandwich.promotion.Shared.domain.patterns.IValueObject;
import gorgeousSandwich.promotion.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.promotion.Util.Validations;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeOfEffect implements IValueObject {
    @Column(name = "start")
    private Date from;
    @Column(name = "end")
    private Date to;

    private TimeOfEffect(Date from, Date to) throws BusinessRuleViolationException {
        try {
            Validations.isTrue(from.before(to));
        } catch (Exception e) {
            throw new BusinessRuleViolationException("Date from is after to!",e);
        }
        try {
            Validations.isTrue(to.after(new Date()));
        } catch (Exception e) {
            throw new BusinessRuleViolationException("Date to is not after now!",e);
        }
        this.from = from;
        this.to = to;
    }



    public static TimeOfEffect of(Date from, Date to) throws BusinessRuleViolationException {
        return new TimeOfEffect(from,to);
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
