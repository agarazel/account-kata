import kata.bank.project.model.account.CurrentAccount
import kata.bank.project.service.TransactionService
import spock.lang.Specification

class DepositSpec extends Specification {

    def 'As a bank client I want to make an initial deposit'() {

        given:
        def account = new CurrentAccount()
        def service = new TransactionService()

        def initialSize = account.operations.size()

        when:
        service.makeDeposit(account, 1000.0)

        then:
        assert account.operations.size() == initialSize + 1
        assert account.amount == 1000.0

    }

}
