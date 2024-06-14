package gorgeousSandwich.user.Controllers;

import gorgeousSandwich.user.Domain.CreatingUserDto;
import gorgeousSandwich.user.Domain.IUserService;
import gorgeousSandwich.user.Domain.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public UserDto login(@Argument CreatingUserDto userDTO) throws NoSuchAlgorithmException {
        List<UserDto> userDTOList = this.userService.getAll();
        UserDto loggedUser = this.userService.getLogin(userDTOList,userDTO);
        if(loggedUser != null) {
            logger.info("User logged in successfully");
            return loggedUser;
        } else {
            logger.error("Cannot find a user with the data inserted");
            return null;
        }
    }

    @MutationMapping
    public CreatingUserDto register(@Argument CreatingUserDto userDTO) {
        try {
            CreatingUserDto dto = this.userService.register(userDTO);
            logger.info("User registered successfully");
            return dto;
        }catch (Exception e) {
            logger.error("Cannot create a user: ", e);
            return null;
        }
    }

    @QueryMapping
    public List<UserDto> getAllUsers() throws NoSuchAlgorithmException {
        try {
            this.userService.getAll();
        } catch (Exception e) {
            logger.error("Cannot list all users", e);
            return null;
        }
        logger.info("All the users were listed");
        Iterable<UserDto> itr = this.userService.getAll();
        List<UserDto> l = new ArrayList<>((Collection<? extends UserDto>) itr);
        return l;
    }


}
