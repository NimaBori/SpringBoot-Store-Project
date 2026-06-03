package dev.nima.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Service
public class OrderService {
  private final PaymentService paymentService;

  // use of annotation
  // public OrderService(@Qualifier("paypal") PaymentService paymentService)
  public OrderService(PaymentService paymentService) {
    // Constructor injection of the PaymentService
    this.paymentService = paymentService;
  }

  public void placeOrder() {
    paymentService.processPayment(100.0);
  }

}
