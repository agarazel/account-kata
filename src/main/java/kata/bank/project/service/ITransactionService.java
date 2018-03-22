package kata.bank.project.service;

import kata.bank.project.model.account.Account;
import kata.bank.project.model.account.CurrentAccount;

public interface ITransactionService {

    void makeDeposit(final Account account, final Double amount);

    void makeWithdrawal(final Account account, final Double amount);

    double getDepositsAmount(final Account account);

    double getWithdrawalsAmount(final Account account);

}
