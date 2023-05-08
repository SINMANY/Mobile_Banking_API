package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.AccountDto;
import co.istad.mbanking.api.account.web.CreateAccountDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    PageInfo<AccountDto> accountPageInfoToAccountDtoPageInfo(PageInfo<Account> accountPageInfo);
    Account mapCreateAccountDtoToAccount(CreateAccountDto createAccountDto);
}
