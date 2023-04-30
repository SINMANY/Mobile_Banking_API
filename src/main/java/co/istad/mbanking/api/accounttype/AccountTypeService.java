package co.istad.mbanking.api.accounttype;


import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateNewAccountTypeDto;

import java.util.List;

public interface AccountTypeService {
     AccountTypeDto createNewAccountType(CreateNewAccountTypeDto createNewAccountTypeDto);
    List<AccountTypeDto> findAll();
    AccountTypeDto findAccountTypeById(Integer id);
    Integer deleteAccountTypeById(Integer id);
}
