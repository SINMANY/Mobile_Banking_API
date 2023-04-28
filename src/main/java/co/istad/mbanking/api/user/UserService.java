package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById( Integer id,boolean status);
    PageInfo<UserDto> fineAllUsers(int page, int limit);
}
