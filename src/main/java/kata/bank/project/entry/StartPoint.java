package kata.bank.project.entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kata.bank.project.configuration.KataConfiguration;
import kata.bank.project.model.account.CurrentAccount;

public class StartPoint {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(KataConfiguration.class);
        CurrentAccount account = context.getBean("actualAccount", CurrentAccount.class);
        account.writeHello();

    }
}
