package kata.bank.project.model.account;

import org.springframework.stereotype.Component;

import static kata.bank.project.model.account.Account.AccountType.CURRENT;

@Component
public class CurrentAccount extends Account {

    private AccountType type;
    private Double overdraft;

    public CurrentAccount() {
        this.type = CURRENT;
    }

    @Override
    public AccountType getType() {
        return type;
    }

    @Override
    public void setType(final AccountType type) {
        this.type = type;
    }

    public Double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(final Double overdraft) {
        this.overdraft = overdraft;
    }

    public void writeHello() {
        System.out.println("Hello !");
    }

    public String testReturnHello() {
        return "Hello !!!";
    }

//    @Override
//    public void makeWithdrawal(final Account account, final Double value) {
//        Transaction transaction = new Transaction(LocalDateTime.now(),
//                                                  account.getType(),
//                                                  WITHDRAWAL,
//                                                  value);
//
//        if (value > super.getAmount() + this.overdraft) throw new AccountTransactionException("Insufficient credit");
//
//        account.setOperations(null == account.getOperations() ? Arrays.asList(transaction)
//                                                              : Arrays.asList((Transaction) account.getOperations(),
//                                                                              transaction));
//        //        account.setAmount(account.getAmount() - value);
//        account.setAmount(null == account.getAmount() ? 0 - value : account.getAmount() - value);
//    }

}
