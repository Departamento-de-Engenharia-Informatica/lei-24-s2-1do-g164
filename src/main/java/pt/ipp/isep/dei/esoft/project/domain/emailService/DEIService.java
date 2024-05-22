package pt.ipp.isep.dei.esoft.project.domain.emailService;

public abstract class DEIService implements EmailService {
    private final String username;
    private final String password;

    public DEIService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String to, String body) {
        System.out.println("Sending email to " + to + " via DEI email service");

    }
}
