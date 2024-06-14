package gorgeousSandwich.user.Domain;

import gorgeousSandwich.user.Shared.domain.valueobjects.Email;
import gorgeousSandwich.user.Shared.domain.valueobjects.Password;
import gorgeousSandwich.user.Shared.domain.valueobjects.TaxIdentification;
import gorgeousSandwich.user.Shared.domain.valueobjects.Username;
import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class UserMapper implements IUserMapper {
    public UserDto toDTO(CreatingUserDto requestBody) throws NoSuchAlgorithmException {
        String email = requestBody.getEmail();
        String password = requestBody.getPassword();
        String taxIdentification = requestBody.getTaxIdentification();
        String username = requestBody.getUsername();
        return new UserDto(null,email,password,taxIdentification,username);
    }

    public UserDto toDTO(User requestBody) throws NoSuchAlgorithmException {
        Long id = requestBody.getId();
        String email = requestBody.getEmail().getEmail();
        String password = requestBody.getPassword().getPassword();
        String taxIdentification = requestBody.getTaxIdentification().getTaxIdentification();
        String username = requestBody.getUsername().getUsername();
        return new UserDto(id,email,password,taxIdentification,username);
    }

    public User toDomain(CreatingUserDto creatingUserDto) throws BusinessRuleViolationException, NoSuchAlgorithmException {
        Long id = creatingUserDto.getId();
        String email = creatingUserDto.getEmail();
        String password = passwordHash(creatingUserDto.getPassword());
        String taxIdentification = creatingUserDto.getTaxIdentification();
        String username = creatingUserDto.getUsername();
        return new User(id,new Email(email), new Password(password), new TaxIdentification(taxIdentification), new Username(username));
    }

    @Override
    public CreatingUserDto toCreateUserDTO(User user) throws NoSuchAlgorithmException {
        Long id = user.getId();
        String email = user.getEmail().getEmail();
        String password = user.getPassword().getPassword();
        String taxIdentification = user.getTaxIdentification().getTaxIdentification();
        String username = user.getUsername().getUsername();

        return new CreatingUserDto(id,email, password, taxIdentification, username);
    }

    public String passwordHash (String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes());
        byte[] bytes = m.digest();
        StringBuilder s = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString() + "A";
    }
}
