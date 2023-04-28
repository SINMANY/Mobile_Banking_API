package co.istad.mbanking.api.accounttype;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> toDto(List<AccountType> model);
    AccountTypeDto toDto(AccountType model);
}
