package co.istad.mbanking.api.account;

import co.istad.mbanking.api.account.web.CreateAccountDto;
import co.istad.mbanking.api.account.web.AccountDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;

    @Override
    public AccountDto createNewUserAccount(CreateAccountDto createAccountDto) {
        Account account = accountMapStruct.mapCreateAccountDtoToAccount(createAccountDto);
        accountMapper.insert(account);
        System.out.println(account);
        return null;
    }

    @Override
    public PageInfo<AccountDto> fineAllUsers(int page, int limit) {
        PageInfo<Account> accountPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(accountMapper::select);
        return accountMapStruct.accountPageInfoToAccountDtoPageInfo(accountPageInfo);

    }

    @Override
    public Integer deleteAccountById(Integer id) {
        boolean isExisted = accountMapper.existsById(id);
        if(isExisted) {
            accountMapper.deleteAccountType(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d not found", id));
    }
}
