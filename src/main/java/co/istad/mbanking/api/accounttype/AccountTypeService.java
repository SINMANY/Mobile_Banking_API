package co.istad.mbanking.api.accounttype;


import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateNewAccountTypeDto;
import co.istad.mbanking.api.accounttype.web.SelectAccountTypeByNameDto;
import co.istad.mbanking.api.accounttype.web.UpdateAccountTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountTypeService {
     AccountTypeDto createNewAccountType(CreateNewAccountTypeDto createNewAccountTypeDto);
    List<AccountTypeDto> findAll();
    AccountTypeDto findAccountTypeById(Integer id);
    AccountTypeDto findAccountTypeByName(SelectAccountTypeByNameDto selectAccountTypeByNameDto);
    AccountTypeDto updateAccountTypeById(Integer id, UpdateAccountTypeDto updateAccountTypeDto);

}
