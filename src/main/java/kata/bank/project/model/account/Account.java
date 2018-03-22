package kata.bank.project.model.account;

import java.time.LocalDateTime;
import java.util.List;

import kata.bank.project.model.operation.Transaction;

import static kata.bank.project.model.account.Account.AccountType.CURRENT;

public abstract class Account {

    public enum AccountType {
        CURRENT,
        SAVINGS
    }

    private AccountType       type;
    private String            accountNumber;
    private Double            amount;
    private AccountStatement  statement;
    private List<Transaction> operations;

    private class AccountStatement {

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

    public Account() {
        this.type = CURRENT;
    }

    public Account(final AccountType type) {
        this.type = (type == null) ? CURRENT : type;
    }

    public Account(final AccountType type,
                   final String accountNumber,
                   final Double amount,
                   final AccountStatement statement, final List<Transaction> operations) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.statement = statement;
        this.operations = operations;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(final AccountType type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
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
