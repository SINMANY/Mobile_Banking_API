package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.*;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    UserDto findUserByStudentCardId(SelectByStudentCardIdDto selectByStudentCardIdDto);
//    UserDto findUserByName(SelectUserByNameDto selectUserByNameDto);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById( Integer id,boolean status);
    PageInfo<UserDto> fineAllUsers(int page, int limit, String name);
    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);
}
