import java.time.LocalDateTime

import kata.bank.project.model.account.CurrentAccount
import kata.bank.project.model.operation.Transaction
import kata.bank.project.service.AccountService
import kata.bank.project.service.TransactionService
import spock.lang.Specification
import spock.lang.Subject

import static kata.bank.project.model.account.Account.AccountType.CURRENT
import static kata.bank.project.model.operation.Transaction.TransactionType.DEPOSIT
import static kata.bank.project.model.operation.Transaction.TransactionType.WITHDRAWAL

class AccountServiceSpec extends Specification {

    def account = new CurrentAccount()
    def transactions = [
      new Transaction(LocalDateTime.parse('2018-03-17T00:00:00'), CURRENT, DEPOSIT, 300.0),
      new Transaction(LocalDateTime.parse('2018-03-18T00:00:00'), CURRENT, DEPOSIT, 50.0),
      new Transaction(LocalDateTime.parse('2018-03-20T00:00:00'), CURRENT, DEPOSIT, 10000.0),
      new Transaction(LocalDateTime.parse('2018-03-21T00:00:00'), CURRENT, WITHDRAWAL, 600.0),
      new Transaction(LocalDateTime.parse('2018-03-22T00:00:00'), CURRENT, WITHDRAWAL, 100.0)
    ]

    def transactionService = new TransactionService()

    @Subject
    AccountService service = new AccountService(transactionService)


    def 'get the account balance between "2018-03-17" and "2018-03-21"'() {

        given:
        account.operations = transactions

        def first = LocalDateTime.parse('2018-03-17T00:00:00')
        def last = LocalDateTime.parse('2018-03-21T00:00:00')

        when:
        def balance = service.buildBalance(account, first, last)

        then:
        println balance
        assert balance == 9750.0

    }

    def 'get the balance before now'() {

        given:
        account.operations = transactions

        def someDate = LocalDateTime.parse('2018-03-20T00:00:00')

        when:
        def balance = service.getBalanceBefore(someDate, account)

        then:
        println balance
        assert balance == 10350.0

    }

}
