package co.istad.mbanking.api.user.web;

import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;


    //    Update status of the user (if user have been deleted, status will be updated and the status changed to "True"
    @PostMapping("/{id}")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody IsDeletedDto dto) {
        Integer deletedId = userService.updateIsDeletedStatusById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been delete successfully")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }


    // Delete a user by ID
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id) {
        Integer deletedId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been delete successfully")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

    // Find a user by ID
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        System.out.println(userDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User found successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    // Create a new User
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User created successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    // select all users as pagination (page and size)
    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(name = "page", required = false,
                                            defaultValue = "20") int limit) {
        PageInfo<UserDto> userDtoPageInfo = userService.fineAllUsers(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User created successfully")
                .timestamp(LocalDateTime.now())
                .data(userDtoPageInfo)
                .build();
    }
}
