package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.accounttype.web.AccountTypeDto;
import co.istad.mbanking.api.accounttype.web.CreateNewAccountTypeDto;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {

    AccountType createNewAccountTypeDtoToUser(CreateNewAccountTypeDto createNewAccountTypeDto);
    AccountTypeDto mapAccountTypeToAccountTypeDto(AccountType accountType);
    List<AccountTypeDto> accountTypeToDotoList(List<AccountType> model);


}
