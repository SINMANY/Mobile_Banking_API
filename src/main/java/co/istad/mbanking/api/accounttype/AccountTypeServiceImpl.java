package co.istad.mbanking.api.accounttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public List<AccountTypeDto> findAll() {
    List<AccountType> accountTypes = accountTypeMapper.select();
        return accountTypeMapStruct.toDto(accountTypes);
    }

}
