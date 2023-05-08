package co.istad.mbanking.api.account.web;

import co.istad.mbanking.api.accounttype.AccountType;



public record AccountDto(String accountNo,
                         String accountName,
                         String profile,
                         Integer pin,
                         String password,
                         String phoneNumber,
                         Integer transferLimit,
                         AccountType accountType) {
}
