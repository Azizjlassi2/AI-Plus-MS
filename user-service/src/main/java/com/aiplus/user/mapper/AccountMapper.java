package com.aiplus.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aiplus.user.dto.AccountDto;
import com.aiplus.user.dto.AdminAccountDto;
import com.aiplus.user.dto.ClientAccountDto;
import com.aiplus.user.dto.DeveloperAccountDto;
import com.aiplus.user.model.Account;
import com.aiplus.user.model.AdminAccount;
import com.aiplus.user.model.ClientAccount;
import com.aiplus.user.model.DeveloperAccount;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Mapping(target = "id", source = "id")

    public abstract DeveloperAccountDto toDto(DeveloperAccount account);

    @Mapping(target = "id", source = "id")
    public abstract AdminAccountDto toDto(AdminAccount account);

    @Mapping(target = "id", source = "id")
    public abstract ClientAccountDto toDto(ClientAccount account);

    public AccountDto toAccountDto(Account account) {
        if (account instanceof DeveloperAccount developerAccount) {
            log.info("Mapping DeveloperAccount to AccountDto");
            return toDto(developerAccount);
        } else if (account instanceof AdminAccount adminAccount) {
            log.info("Mapping AdminAccount to AccountDto");
            return toDto(adminAccount);
        } else if (account instanceof ClientAccount clientAccount) {
            log.info("Mapping ClientAccount to AccountDto");
            return toDto(clientAccount);
        } else {
            return null;
        }
    }
}