package dev.nima.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// @Service("stripe")
// @Primary
public class StripePaymentService implements PaymentService {
  @Value("${stripe.apiUrl}")
  private String apiUrl;

  @Override
  public void processPayment(double amount) {
    // Simulate payment processing with Stripe
    System.out.println("Processing payment of $" + amount + " through Stripe...");
    // Here you would integrate with the Stripe API to process the payment
  }
}
