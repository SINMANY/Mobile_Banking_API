package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;
import co.istad.mbanking.api.account.web.CreateAccountDto;
import co.istad.mbanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/create-new-account")
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateAccountDto createAccountDto) {
        AccountDto accountDto = accountService.createNewUserAccount(createAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllAccounts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        PageInfo<AccountDto> accountDtoPageInfo = accountService.fineAllUsers(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Find all users successfully")
                .timestamp(LocalDateTime.now())
                .data(accountDtoPageInfo)
                .build();
    }
}
