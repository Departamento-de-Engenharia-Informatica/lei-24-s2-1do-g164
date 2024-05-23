package pt.ipp.isep.dei.esoft.project.application.session.emailService.adapters;

import pt.ipp.isep.dei.esoft.project.application.session.emailService.EmailService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GmailService implements EmailService {

    @Override
    public void sendEmail(String name, String body) {
        try (PrintWriter out = new PrintWriter(new FileWriter("emails.txt", true))) {
            out.println("To: " + name);
            out.println("Body: " + body);
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

