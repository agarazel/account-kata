package kata.bank.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public TransactionService() {
    }

    @Override
    public void makeDeposit(final Account account, final Double value) {
        Transaction transaction = new Transaction(LocalDateTime.now(),
                                                  account.getType(),
                                                  DEPOSIT,
                                                  value);
        account.getOperations()
               .add(transaction);
        account.setAmount(value);
    }

    @Override
    public void makeWithdrawal(final Account account, final Double value) {
        Transaction transaction = new Transaction(LocalDateTime.now(),
                                                  account.getType(),
                                                  WITHDRAWAL,
                                                  value);
        if (account instanceof CurrentAccount) {
            if (value > account.getAmount() + ((CurrentAccount) account).getOverdraft()) {
                throw new AccountTransactionException("Insufficient credit");
            }
            account.getOperations()
                   .add(transaction);
            account.setAmount(account.getAmount() - value);
        } else {
            if (value > account.getAmount()) { throw new AccountTransactionException("Insufficient credit"); }
        }
    }

    @Override
    public long returnTransactionsCountBefore(final LocalDateTime dateTime, final Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isBefore(dateTime))
                      .count();
    }

    @Override
    public long returnTransactionsCountAfter(final LocalDateTime dateTime, final Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isAfter(dateTime))
                      .count();
    }

    @Override
    public double getDepositsAmount(final Account account) {
        return this.returnTotalAmountInAccountByType(DEPOSIT, account);
    }

    @Override
    public double getWithdrawalsAmount(final Account account) {
        return this.returnTotalAmountInAccountByType(WITHDRAWAL, account) * -1;
    }

    private double returnTotalAmountInAccountByType(final TransactionType transactionType, final Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> transactionType.equals(o.getType()))
                      .map(Transaction::getAmount)
                      .mapToDouble(Double::doubleValue)
                      .sum();
    }

    @Override
    public double calculateOperationsAmount(final List<Transaction> operations) {
        return operations.stream()
                         .map(o -> {
                             if (WITHDRAWAL.equals(o.getType())) {
                                 return -o.getAmount();
                             } else {
                                 return o.getAmount();
                             }
                         })
                         .mapToDouble(Double::doubleValue)
                         .sum();
    }

    private double returnOperationsAmount(final List<Transaction> operations) {
        operations.forEach(o -> o.setAmount(WITHDRAWAL.equals(o.getType()) ? -o.getAmount() : o.getAmount()));
        return operations.stream()
                         .map(Transaction::getAmount)
                         .peek(System.out::println)
                         .mapToDouble(Double::doubleValue)
                         .sum();
    }

}
