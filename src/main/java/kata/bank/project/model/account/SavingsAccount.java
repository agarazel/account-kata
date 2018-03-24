package kata.bank.project.model.account;

import java.util.List;

import org.springframework.stereotype.Component;

import kata.bank.project.model.operation.Transaction;

import static kata.bank.project.model.account.Account.AccountType.SAVINGS;

@Component
public class SavingsAccount extends Account {

    private AccountType type = SAVINGS;
    private Double savingRatio;

    public SavingsAccount() {
        super(SAVINGS);
        this.savingRatio = 0.0;
    }

    public SavingsAccount(Double savingRatio) {
        this();
        this.savingRatio = savingRatio;
    }

    public SavingsAccount(final int accountNumber,
                          final Double amount,
                          final AccountStatement statement,
                          final List<Transaction> operations,
                          final Double savingRatio) {
        super(SAVINGS, accountNumber, amount, statement, operations);
        this.savingRatio = savingRatio;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public void setType(final AccountType type) {
        this.type = type;
    }

    public Double getSavingRatio() {
        return savingRatio;
    }

    public void setSavingRatio(final Double savingRatio) {
        this.savingRatio = savingRatio;
    }
}
