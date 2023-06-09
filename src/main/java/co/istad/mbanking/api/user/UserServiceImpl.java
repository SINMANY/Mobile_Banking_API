package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
//@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

//    Create a new user
    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.mapCreateUserDtoToUser(createUserDto);
        userMapper.insert(user);
        return this.findUserById(user.getId());
    }

//    Find User by ID
    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d not found", id)));
        return userMapStruct.mapUserToUserDto(user);
    }


    @Override
    public UserDto fineUserBySCI(String studentCardId) {
        User user = userMapper.selectBySCI(studentCardId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with id %s not found", studentCardId)));
        return userMapStruct.mapUserToUserDto(user);
    }

    //    Delete user by ID
    @Override
    public UserDto deleteUserById(Integer id) {
        if(userMapper.existsById(id)){
            UserDto userDto = findUserById(id);
            userMapper.updateDeletedById(id, true);
            return userDto;
        }
// throw exception
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d is not fount", id));
    }

//    Update deleted Status of a user
    @Override
    public Integer updateIsDeletedStatusById( Integer id,boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d not found", id));
    }

    @Override
    public PageInfo<UserDto> fineAllUsers(int page, int limit, String name) {
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> userMapper.select(name));
        return userMapStruct.userDtoToPageInfoUserDtoPageInfo(userPageInfo);
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if (userMapper.existsById(id)) {
            User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
}
