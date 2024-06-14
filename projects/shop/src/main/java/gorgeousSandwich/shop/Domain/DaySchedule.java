package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Shared.domain.patterns.IEntity;
import gorgeousSandwich.shop.Shared.domain.patterns.IEntityId;
import gorgeousSandwich.shop.Shared.domain.patterns.IValueObject;
import gorgeousSandwich.shop.Shared.domain.valueobjects.Schedule;
import gorgeousSandwich.shop.Shared.exceptions.BusinessRuleViolationException;
import gorgeousSandwich.shop.Util.Validations;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DaySchedule {
    private Schedule schedule;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    DaySchedule(Schedule schedule) throws BusinessRuleViolationException {
        try {
            this.schedule = schedule;
        } catch (Exception e) {
            throw new BusinessRuleViolationException(e);
        }
    }

    DaySchedule(Long id, Schedule schedule) throws BusinessRuleViolationException {
        try {
            this.id = id;
            this.schedule = schedule;
        } catch (Exception e) {
            throw new BusinessRuleViolationException(e);
        }
    }


}
