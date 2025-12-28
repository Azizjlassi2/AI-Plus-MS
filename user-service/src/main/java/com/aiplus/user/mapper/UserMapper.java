package com.aiplus.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.aiplus.user.dto.AccountDto;
import com.aiplus.user.dto.UserCreationDto;
import com.aiplus.user.dto.UserDetailDto;
import com.aiplus.user.dto.UsersDto;
import com.aiplus.user.model.Account;
import com.aiplus.user.model.Role;
import com.aiplus.user.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected AccountMapper accountMapper;

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "username")
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    public abstract UsersDto toDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "username")
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    @Mapping(source = "account", target = "account", qualifiedByName = "accountToDto")
    public abstract UserDetailDto toDetailDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(source = "username", target = "name")
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    public abstract User toEntity(UserCreationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(source = "username", target = "name")
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    public abstract User toEntity(UserDetailDto dto);

    @Named("roleToString")
    public String roleToString(Role role) {
        return role != null ? role.name() : null;
    }

    @Named("stringToRole")
    public Role stringToRole(String role) {
        return role != null ? Role.valueOf(role) : null;
    }

    @Named("accountToDto")
    public AccountDto accountToDto(Account account) {
        return account != null ? accountMapper.toAccountDto(account) : null;
    }

    @Mapping(target = "account", ignore = true)
    @Mapping(source = "username", target = "name")
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToRole")
    public abstract User toEntity(UsersDto dto);
}