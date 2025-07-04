package utils;

public class Mail {
    private String to;
    private String subject;
    private String message;

    public Mail(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }
}
