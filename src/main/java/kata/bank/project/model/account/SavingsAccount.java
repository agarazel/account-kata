package kata.bank.project.model.account;

import org.springframework.stereotype.Component;

import static kata.bank.project.model.account.Account.AccountType.SAVINGS;

@Component
public class SavingsAccount extends Account {

    private AccountType type;
    private Double savingRatio;

    public SavingsAccount() {
        this.type = SAVINGS;
    }

    public SavingsAccount(final AccountType type) {
        this.type = SAVINGS;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(final AccountType type) {
        this.type = type;
    }
}
