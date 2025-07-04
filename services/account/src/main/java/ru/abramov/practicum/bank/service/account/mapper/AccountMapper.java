package ru.abramov.practicum.bank.service.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abramov.practicum.bank.service.account.dto.AccountDto;
import ru.abramov.practicum.bank.service.account.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", source = "id")
    AccountDto toDto(Account account);
}
