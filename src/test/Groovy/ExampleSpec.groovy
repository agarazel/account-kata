import java.time.LocalDateTime

import kata.bank.project.model.account.Account
import kata.bank.project.model.account.CurrentAccount
import kata.bank.project.model.operation.Transaction
import spock.lang.Specification
import spock.lang.Subject

class ExampleSpec extends Specification {

    @Subject
    def account = new CurrentAccount()

    def dateTime1 = LocalDateTime.parse('2018-03-19T14:05:01')
    def dateTime2 = LocalDateTime.parse('2018-03-19T14:15:01')

    def 'Start testing'() {
        given:
        def result = account.testReturnHello()
        when:
        account.testReturnHello()
        then:
        assert result == 'Hello !!!'
    }

    def 'Test with dates'() {
        when:
        account.setOperations([
          new Transaction(dateTime1, Account.AccountType.CURRENT, Transaction.TransactionType.DEPOSIT, 100),
          new Transaction(dateTime2, Account.AccountType.CURRENT, Transaction.TransactionType.DEPOSIT, 100)
        ])
        then:
        assert dateTime1.isBefore(dateTime2)
    }

}
