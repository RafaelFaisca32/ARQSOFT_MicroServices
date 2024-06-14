package gorgeousSandwich.user.Domain;

import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;

import java.security.NoSuchAlgorithmException;

public interface IUserMapper {
    public UserDto toDTO(CreatingUserDto requestBody) throws NoSuchAlgorithmException;
    public UserDto toDTO(User requestBody) throws NoSuchAlgorithmException;
    public User toDomain(CreatingUserDto createSandwich) throws BusinessRuleViolationException, NoSuchAlgorithmException;
    public CreatingUserDto toCreateUserDTO(User user) throws NoSuchAlgorithmException;
    public String passwordHash (String password) throws NoSuchAlgorithmException;

}
