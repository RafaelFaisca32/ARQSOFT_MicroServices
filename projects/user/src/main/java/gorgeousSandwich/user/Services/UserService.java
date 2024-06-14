package gorgeousSandwich.user.Services;

import gorgeousSandwich.user.Domain.*;
import gorgeousSandwich.user.Shared.exceptions.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll () throws NoSuchAlgorithmException {
        List<User> userList = this.userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList) {
            userDtoList.add(userMapper.toDTO(user));
        }
        return userDtoList;
    }

    public UserDto getLogin(List<UserDto> userDtoList, CreatingUserDto userDTO) throws NoSuchAlgorithmException {
        UserDto exists = null;
        for(UserDto user : userDtoList) {
            String password = passwordHash(userDTO.getPassword());
            if(user.getEmail().equals(userDTO.getEmail()) && user.getPassword().equals(password)){
                exists = user;
            }
        }
        return exists;
    }

    public CreatingUserDto register(CreatingUserDto dto) throws BusinessRuleViolationException, NoSuchAlgorithmException {
        return this.userMapper.toCreateUserDTO(this.userRepository.save(userMapper.toDomain(dto)));
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
