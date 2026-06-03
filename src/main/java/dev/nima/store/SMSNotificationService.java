package dev.nima.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService {
  @Override
  public void sendNotification(String message, String recipientEmail) {
    // Simulate sending an SMS notification
    System.out.println("Sending SMS notification to: " + recipientEmail + " with message: " + message);
    // Here you would integrate with an SMS gateway to send the SMS
  }

}
