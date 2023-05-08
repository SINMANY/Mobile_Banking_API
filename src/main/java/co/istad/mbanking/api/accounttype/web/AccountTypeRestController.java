package co.istad.mbanking.api.accounttype.web;

import co.istad.mbanking.api.accounttype.AccountTypeService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;

    @PostMapping
    public BaseRest<?> createNewAccountType(@Valid @RequestBody CreateNewAccountTypeDto createNewAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.createNewAccountType(createNewAccountTypeDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Your account created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();
    }

    @GetMapping("/id/{id}")
    public BaseRest<?>  findAccountTypeById(@PathVariable Integer id){
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type found successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @GetMapping("/name")
    public BaseRest<?> findAccountTypeByName(@Valid @RequestBody SelectAccountTypeByNameDto selectAccountTypeByNameDto){
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeByName(selectAccountTypeByNameDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type found successfully")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@Valid @PathVariable("id") Integer id,
                                      @RequestBody UpdateAccountTypeDto updateAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.updateAccountTypeById(id, updateAccountTypeDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build(); }

}
