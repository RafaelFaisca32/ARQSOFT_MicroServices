package gorgeousSandwich.user.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, UserId> {

    List<User> findAll();
}
