package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Util.Validations;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Manager {
    private String managerName;
    private Long managerId;

    public Manager(String managerName, Long managerId) {
        Validations.notAnyNull(managerName, managerId);
        Validations.notEmpty(managerName);
        this.managerName = managerName;
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }
}
