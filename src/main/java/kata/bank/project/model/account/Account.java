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

        private LocalDateTime start;
        private LocalDateTime end;
        private Double        balance; // with transactions awaiting

        public AccountStatement(final LocalDateTime start,
                                final LocalDateTime end,
                                final Double balance) {
            this.start = start;
            this.end = end;
            this.balance = balance;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public void setStart(final LocalDateTime start) {
            this.start = start;
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public void setEnd(final LocalDateTime end) {
            this.end = end;
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
            new ArrayList<>(0)
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
        operations.forEach(o -> amount += o.getAmount());
    }

    //    public abstract void makeWithdrawal(final Account account, final Double value);

}
