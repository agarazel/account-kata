package kata.bank.project.service;

import java.time.LocalDateTime;

import kata.bank.project.model.account.Account;

public interface IAccountService {

    Double buildBalance(final Account account, final LocalDateTime start, final LocalDateTime end);

}
