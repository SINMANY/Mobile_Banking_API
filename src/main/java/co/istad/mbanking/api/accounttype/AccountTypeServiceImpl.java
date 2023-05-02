package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateNewAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.SelectAccountTypeByNameDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

//    Create a new account type
    @Override
    public AccountTypeDto createNewAccountType(CreateNewAccountTypeDto createNewAccountTypeDto) {
        AccountType accountType = accountTypeMapStruct.createNewAccountTypeDtoToUser(createNewAccountTypeDto);
        accountTypeMapper.insert(accountType);
        return null;
    }


//    Find All account types
    @Override
    public List<AccountTypeDto> findAll() {
    List<AccountType> accountTypes = accountTypeMapper.select();
        return accountTypeMapStruct.accountTypeToDotoList(accountTypes);
    }

//    Find Account Type by id
    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Account type with id %d not found", id)));
//        System.out.println(accountType);
        return accountTypeMapStruct.mapAccountTypeToAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto findAccountTypeByName(SelectAccountTypeByNameDto selectAccountTypeByNameDto) {
        AccountType accountType = accountTypeMapper.selectByName(selectAccountTypeByNameDto).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Account type with name '%s' not found", selectAccountTypeByNameDto)));
        return accountTypeMapStruct.mapAccountTypeToAccountTypeDto(accountType);
    }


    //    Delete Account Type by id
    @Override
    public Integer deleteAccountTypeById(Integer id) {
        boolean isExisted = accountTypeMapper.existsById(id);
        if(isExisted){
            accountTypeMapper.deleteAccountById(id);
            return  id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d not found", id));
    }

    @Override
    public AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto) {
        if (accountTypeMapper.existsById(id)) {
            AccountType accountType = accountTypeMapStruct.updateAccountTypeDtoToAccountType(updateAccountTypeDto);
            accountType.setId(id);
            accountTypeMapper.updateById(accountType);
            return this.findAccountTypeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
}
