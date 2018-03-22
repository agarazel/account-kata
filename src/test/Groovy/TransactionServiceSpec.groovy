import java.time.LocalDateTime

import kata.bank.project.model.account.CurrentAccount
import kata.bank.project.model.operation.Transaction
import kata.bank.project.service.TransactionService
import spock.lang.Specification
import spock.lang.Subject

import static java.time.LocalDateTime.now
import static kata.bank.project.model.account.Account.AccountType.CURRENT
import static kata.bank.project.model.operation.Transaction.TransactionType.DEPOSIT
import static kata.bank.project.model.operation.Transaction.TransactionType.WITHDRAWAL

class TransactionServiceSpec extends Specification {

    @Subject
    def service = new TransactionService()

    def account = new CurrentAccount()
    def transactions = [
      new Transaction(now(), CURRENT, DEPOSIT, 300.0),
      new Transaction(now(), CURRENT, DEPOSIT, 50.0),
      new Transaction(now(), CURRENT, DEPOSIT, 10000.0),
      new Transaction(now(), CURRENT, WITHDRAWAL, 1100.0),
      new Transaction(now(), CURRENT, WITHDRAWAL, 100.0)
    ]

    def 'It should return sum of the deposits'() {

        given:
        account.setOperations(transactions)

        when:
        def sum = service.getDepositsAmount(account)

        then:
        assert sum == 10350

    }

    def 'It should return sum of the withdrawals'() {

        given:
        account.setOperations(transactions)

        when:
        def sum = service.getWithdrawalsAmount(account)

        then:
        assert sum == 1200

    }

    def 'It should return the number of transactions after "2017-06-25T05:20:24"'() {

        given:
        def someDateTime = LocalDateTime.parse('2017-06-25T05:20:24')
        transactions.add(new Transaction(someDateTime, CURRENT, DEPOSIT, 100.0))
        account.setOperations(transactions)

        when:
        //        def amount = service.getAmount(account)
//        def resultList = service.returnTransactionsBeforeNow(account)
        def countResult = service.returnTransactionsCountAfter(someDateTime, account)

        then:
        assert countResult == 5

    }

    def 'It should add a new deposit if there are no transactions in account'() {

        given:
        service.makeDeposit(account, 1000.0)

        when:
        def transactionsCount = account.operations.size()
        def depositsAmount = service.getDepositsAmount(account)

        then:
        assert transactionsCount == 1
        assert depositsAmount == 1000.0

    }

}
