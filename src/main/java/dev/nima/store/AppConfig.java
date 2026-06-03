package dev.nima.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Value("${payment-service:stripe}")
  private String paymentService;

  @Bean
  public PaymentService paypal() {
    return new PayPalPaymentService();
  }

  @Bean
  public PaymentService stripe() {
    return new StripePaymentService();
  }

  @Bean
  public OrderService orderService() {
    if (paymentService.equals("paypal")) {
      return new OrderService(paypal());
    } else {
      return new OrderService(stripe());
    }
  }
}
