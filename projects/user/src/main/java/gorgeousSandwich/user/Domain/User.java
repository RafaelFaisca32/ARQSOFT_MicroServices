package gorgeousSandwich.user.Domain;
import gorgeousSandwich.user.Shared.domain.valueobjects.Email;
import gorgeousSandwich.user.Shared.domain.valueobjects.Password;
import gorgeousSandwich.user.Shared.domain.valueobjects.TaxIdentification;
import gorgeousSandwich.user.Shared.domain.valueobjects.Username;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Email email;
    @Embedded
    private Password password;
    @Embedded
    private TaxIdentification taxIdentification;
    @Embedded
    private Username username;

    public User(Long id, Email email, Password password, TaxIdentification taxIdentification, Username username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.taxIdentification = taxIdentification;
        this.username = username;
    }
}