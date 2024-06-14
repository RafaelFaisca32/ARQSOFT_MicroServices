package gorgeousSandwich.user.Domain;

import gorgeousSandwich.user.Shared.domain.patterns.IEntityId;

public class UserId implements IEntityId {


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
