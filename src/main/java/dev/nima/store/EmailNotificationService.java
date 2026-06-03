package dev.nima.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {

  private final String host;
  private final int port;

  public EmailNotificationService(
      @Value("${email.host}") String host,
      @Value("${email.port}") int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void sendNotification(String message, String recipientEmail) {
    // Simulate sending an email notification
    System.out.println("Sending email notification to: " + recipientEmail);
    System.out.println("Mail Server: " + host + ":" + port);
    System.out.println("Message: " + message);
    // Here you would integrate with an email service provider to send the email
  }

}
