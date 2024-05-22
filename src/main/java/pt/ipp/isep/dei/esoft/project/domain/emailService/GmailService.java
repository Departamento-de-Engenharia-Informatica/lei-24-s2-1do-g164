package pt.ipp.isep.dei.esoft.project.domain.emailService;

import java.util.Properties;

public abstract class GmailService implements EmailService {

    private final String username;
    private final String password;

    public GmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String to, String body) {
        System.out.println("Sending email to " + to + " via Gmail service");
    }



    }

