package kata.bank.project.model.operation;

import kata.bank.project.model.account.Account.AccountType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class Transaction {

    private int             numID;
    private LocalDateTime   transactionDateTime;
    private AccountType     accountType;
    private TransactionType type;
    private Double          amount;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    public Transaction() {
    }

    public Transaction(final LocalDateTime transactionDateTime,
                       final AccountType accountType,
                       final TransactionType type,
                       final Double amount) {
        this.numID = (new Random()).nextInt(1000000) + 1;
        this.transactionDateTime = transactionDateTime;
        this.accountType = accountType;
        this.type = type;
        this.amount = amount;
    }

    public int getNumID() {
        return numID;
    }

    public void setNumID(final int numID) {
        this.numID = numID;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(final LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(final TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }
}
