package co.istad.mbanking.api.account;


import co.istad.mbanking.api.account.web.CreateAccountDto;
import co.istad.mbanking.api.account.web.AccountDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AccountDto createNewUserAccount(CreateAccountDto createAccountDto);
    PageInfo<AccountDto> fineAllUsers(int page, int limit);
    Integer deleteAccountById(Integer id);
}
