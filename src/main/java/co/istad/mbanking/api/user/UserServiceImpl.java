package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        System.out.println(user);
        return userMapStruct.mapUserToUserDto(user);
    }

//    Delete user by ID
    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted){
            userMapper.deleteById(id);
            return id;
        }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
              String.format("User with id %d not found", id));
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
    public PageInfo<UserDto> fineAllUsers(int page, int limit) {
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(userMapper::select);

        return userMapStruct.userDtoToPageInfoUserDtoPageInfo(userPageInfo);
    }

}
