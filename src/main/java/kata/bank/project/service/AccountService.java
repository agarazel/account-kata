package kata.bank.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import kata.bank.project.model.account.Account;
import kata.bank.project.model.operation.Transaction;

public class AccountService implements IAccountService {

    private final TransactionService service;

    @Autowired
    public AccountService(final TransactionService service) {
        this.service = service;
    }

    @Override
    public Double buildBalance(final Account account, final LocalDateTime start, final LocalDateTime end) {

        List<Transaction> operationsInBalance = account.getOperations()
                                                       .stream()
                                                       .filter(o -> (o.getTransactionDateTime()
                                                                      .isAfter(start)
                                                                     ||
                                                                     o.getTransactionDateTime()
                                                                      .equals(start)
                                                                    )
                                                                    &&
                                                                    (o.getTransactionDateTime()
                                                                      .isBefore(end)
                                                                     ||
                                                                     o.getTransactionDateTime()
                                                                      .equals(end)
                                                                    )
                                                              )
                                                       .collect(Collectors.toList());

        return service.calculateOperationsAmount(operationsInBalance);
    }

    @Override
    public Double getBalanceBefore(final LocalDateTime dateTime, final Account account) {
        return account.getOperations()
                      .stream()
                      .filter(o -> o.getTransactionDateTime()
                                    .isBefore(dateTime)
                                   ||
                                   o.getTransactionDateTime()
                                    .equals(dateTime))
                      .map(Transaction::getAmount)
                      .mapToDouble(Double::doubleValue)
                      .sum();
    }
}
