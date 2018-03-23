package kata.bank.project.model.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kata.bank.project.model.operation.Transaction;

public abstract class Account {

    public enum AccountType {
        CURRENT,
        SAVINGS
    }

    private AccountType       type;
    private int               accountNumber;
    private Double            amount;
    private AccountStatement  statement;
    private List<Transaction> operations;

    public class AccountStatement {

        private LocalDateTime dateTime;
        private Double        balance; // with transactions awaiting

        public AccountStatement(final LocalDateTime dateTime,
                                final Double balance) {
            this.dateTime = dateTime;
            this.balance = balance;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(final LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(final Double balance) {
            this.balance = balance;
        }
    }

    public Account(final AccountType type,
                   final int accountNumber,
                   final Double amount,
                   final AccountStatement statement,
                   final List<Transaction> operations) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.statement = statement;
        this.operations = operations;
    }

    public Account(final AccountType type) {
        this(
            type,
            (new Random()).nextInt(1000000) + 1,
            0.0,
            null, // TODO correct statement initialisation
            new ArrayList<Transaction>(0)
            );
    }

    public Account() {}

    public AccountType getType() {
        return type;
    }

    public void setType(final AccountType type) {
        this.type = type;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public AccountStatement getStatement() {
        return statement;
    }

    public void setStatement(final AccountStatement statement) {
        this.statement = statement;
    }

    public List<Transaction> getOperations() {
        return operations;
    }

    public void setOperations(final List<Transaction> operations) {
        this.operations = operations;
    }

    //    public abstract void makeWithdrawal(final Account account, final Double value);

}
