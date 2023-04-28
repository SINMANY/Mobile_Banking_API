package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User mapCreateUserDtoToUser(CreateUserDto createUserDto);
    UserDto mapUserToUserDto(User user);
    User mapUserDtoToUser(UserDto user);
    PageInfo<UserDto> userDtoToPageInfoUserDtoPageInfo(PageInfo<User> userPageInfo);
}
