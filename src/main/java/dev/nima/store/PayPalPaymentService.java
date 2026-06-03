package dev.nima.store;

import org.springframework.stereotype.Service;

// @Service("paypal")
public class PayPalPaymentService implements PaymentService {
  @Override
  public void processPayment(double amount) {
    // Simulate payment processing with PayPal
    System.out.println("Processing payment of $" + amount + " through PayPal...");
    // Here you would integrate with the PayPal API to process the payment
  }

}
