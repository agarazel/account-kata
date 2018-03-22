package kata.bank.project.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountException;

import org.springframework.stereotype.Service;

import kata.bank.project.exception.AccountTransactionException;
import kata.bank.project.model.account.Account;
import kata.bank.project.model.account.CurrentAccount;
import kata.bank.project.model.operation.Transaction;
import kata.bank.project.model.operation.Transaction.TransactionType;

import static kata.bank.project.model.operation.Transaction.TransactionType.DEPOSIT;
import static kata.bank.project.model.operation.Transaction.TransactionType.WITHDRAWAL;

@Service
public class TransactionService implements ITransactionService {

    @Override
    public void makeDeposit(final Account account, final Double value) {
        Transaction transaction = new Transaction(LocalDateTime.now(),
                                                  account.getType(),
                                                  DEPOSIT,
                                                  value);

        //        if (null == account.getOperations()) {
        //            account.setOperations(Arrays.asList(transaction));
        //        } else {
        //            account.getOperations()
        //                   .add(transaction);
        //        }
        account.setOperations(null == account.getOperations() ? Arrays.asList(transaction)
                                                              : Arrays.asList((Transaction) account.getOperations(),
                                                                              transaction));
        account.setAmount(null == account.getAmount() ? value : account.getAmount() + value);
    }

    @Override
    public void makeWithdrawal(final Account account, final Double value) {
        Transaction transaction = new Transaction(LocalDateTime.now(),
                                                  account.getType(),
                                                  WITHDRAWAL,
                                                  value);

        if (account instanceof CurrentAccount) {
            if (value > account.getAmount() + ((CurrentAccount) account).getOverdraft()) throw new AccountTransactionException("Insufficient credit");
            account.setOperations(null == account.getOperations() ? Arrays.asList(transaction)
                                                                  : Arrays.asList((Transaction) account.getOperations(), transaction));
            //        account.setAmount(account.getAmount() - value);
            account.setAmount(null == account.getAmount() ? 0 - value : account.getAmount() - value);
        } else {
            if (value > account.getAmount()) throw new AccountTransactionException("Insufficient credit");
        }
    }

    public List<Transaction> returnTransactionsBeforeNow(Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isBefore(LocalDateTime.now()))
                      .collect(Collectors.toList());
    }

    public long returnTransactionsCountBeforeNow(Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isBefore(LocalDateTime.now()))
                      .count();
    }

    public long returnTransactionsCountAfter(LocalDateTime dateTime, Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isAfter(dateTime))
                      .count();
    }

    @Override
    public double getDepositsAmount(final Account account) {
        return this.getAmountOf(DEPOSIT, account);

    }

    @Override
    public double getWithdrawalsAmount(final Account account) {
        return this.getAmountOf(WITHDRAWAL, account);
    }

    private double getAmountOf(final TransactionType transactionType, final Account account) {
        List<Transaction> operations = account.getOperations();
        return operations.stream()
                         .filter(o -> transactionType.equals(o.getType()))
                         .map(Transaction::getAmount)
                         .mapToDouble(d -> d)
                         .sum();
    }

}
