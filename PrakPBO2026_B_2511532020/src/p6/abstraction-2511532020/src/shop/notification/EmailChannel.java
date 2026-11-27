package shop.notification;

public class EmailChannel extends AbstractChannel {
    @Override
    public String channelName() { return "EMAIL"; }

    @Override
    protected String resolveRecipient(CustomerContact contact) {
        String email = contact.getEmail();
        return (email != null && email.contains("@")) ? email : null;
    }

    @Override
    protected boolean deliver(String recipient, String formattedMessage) {
        System.out.println("[EMAIL to " + recipient + "] " + formattedMessage);
        return true;
    }
}