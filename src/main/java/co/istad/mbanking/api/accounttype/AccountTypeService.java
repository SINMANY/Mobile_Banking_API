package co.istad.mbanking.api.accounttype;


import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateNewAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.SelectAccountTypeByNameDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;

import java.util.List;

public interface AccountTypeService {
     AccountTypeDto createNewAccountType(CreateNewAccountTypeDto createNewAccountTypeDto);
    List<AccountTypeDto> findAll();
    AccountTypeDto findAccountTypeById(Integer id);
    AccountTypeDto findAccountTypeByName(SelectAccountTypeByNameDto selectAccountTypeByNameDto);
    Integer deleteAccountTypeById(Integer id);
    AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto);

}
