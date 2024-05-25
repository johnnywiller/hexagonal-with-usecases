package hexagonal.app.payment.infrastructure;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.joda.money.CurrencyUnit.USD;

import java.util.Set;

import org.joda.money.Money;
import org.springframework.scheduling.annotation.Scheduled;

import hexagonal.app.payment.domain.Basket;
import hexagonal.app.payment.domain.CreatePaymentCommand;
import hexagonal.app.payment.domain.Product;
import hexagonal.app.payment.domain.port.driver.CreatePaymentUseCaseFactory;
import hexagonal.app.payment.domain.port.driver.UseCase;

public class PaymentScheduler {

    private final CreatePaymentUseCaseFactory createPaymentUseCaseFactory;

    public PaymentScheduler(CreatePaymentUseCaseFactory createPaymentUseCaseFactory) {
        this.createPaymentUseCaseFactory = createPaymentUseCaseFactory;
    }

    @Scheduled(fixedRate = 1, timeUnit = SECONDS)
    public void processPayments() {
        // Prepare the CreatePaymentCommand with a sample basket
        Set<Product> products = Set.of(
                new Product("Product 1", Money.of(USD, 1)),
                new Product("Product 2", Money.of(USD, 3))
        );
        Basket basket = new Basket(products);
        CreatePaymentCommand command = new CreatePaymentCommand(basket);

        // Create and execute the use case
        UseCase createPaymentUseCase = createPaymentUseCaseFactory.createPaymentUseCase(command);
        createPaymentUseCase.execute();
    }
}
