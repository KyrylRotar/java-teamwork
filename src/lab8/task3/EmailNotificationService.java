package lab8.task3;

public class EmailNotificationService implements IEmailNotifier {
    @Override
    public void sendNotification(String email, String message) {
        System.out.println("[Email] Нагадування надіслано на " + email + ": " + message);
    }
}