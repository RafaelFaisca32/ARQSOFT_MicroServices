package gorgeousSandwich.shop.Domain;

import gorgeousSandwich.shop.Shared.domain.patterns.EntityId;
import gorgeousSandwich.shop.Shared.domain.patterns.IEntityId;
import jakarta.persistence.*;

@Embeddable
public class UserId implements IEntityId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public UserId() {
    }

    public UserId(Long id) {
        this.id = id;
    }


    @Override
    public Long id() {
        return id;
    }
}
