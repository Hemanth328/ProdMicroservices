package com.pbs.mapper;

import com.pbs.dto.AccountsDto;
import com.pbs.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(AccountsDto accountsDto, Accounts accounts) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;
    }

    public static Accounts mapToAccounts(Accounts accounts, AccountsDto accountsDto) {

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accounts.getBranchAddress());

        return accounts;
    }
}
