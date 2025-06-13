package ru.abramov.practicum.bank.service.account.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abramov.practicum.bank.service.account.model.Currency;
import ru.abramov.practicum.bank.service.account.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAllByUserId(String userId);

    Boolean existsAccountByNumber(String number);

    Boolean existsByCurrencyAndUserId(Currency currency, String userId);
}
