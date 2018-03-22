package kata.bank.project.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import kata.bank.project.model.account.CurrentAccount;

@Configuration
@ComponentScan("kata.bank.project.model")
public class KataConfiguration {

    @Bean(value = "actualAccount")
    CurrentAccount getCurrentAccout() {
        return new CurrentAccount();
    }
}
