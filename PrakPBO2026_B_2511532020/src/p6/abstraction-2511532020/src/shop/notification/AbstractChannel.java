package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount = 0;
    private String lastDelivered = "";

    public int getSentCount() { return sentCount; }
    public String getLastDelivered() { return lastDelivered; }

    protected abstract String resolveRecipient(CustomerContact contact);
    protected abstract boolean deliver(String recipient, String formattedMessage);

    protected String prepare(String message) { return message; }

    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (message == null || message.isBlank()) return false;
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()) return false;

        String preparedMsg = prepare(message);
        boolean ok = deliver(recipient, preparedMsg);
        if (ok) {
            sentCount++;
            lastDelivered = preparedMsg;
        }
        return ok;
    }
}