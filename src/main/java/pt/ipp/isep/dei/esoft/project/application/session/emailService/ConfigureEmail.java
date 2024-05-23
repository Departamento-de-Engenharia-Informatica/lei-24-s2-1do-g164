package pt.ipp.isep.dei.esoft.project.application.session.emailService;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigureEmail {

    private static final String CONFIG_FILE = "config.properties";

    public static EmailService createEmailService() {
        try {
            Properties props = new Properties();
            FileInputStream in=new FileInputStream(CONFIG_FILE);
            props.load(in);
            in.close();

            String emailServiceClassName = props.getProperty("email.service");

            Class<?> emailServiceClass = Class.forName(emailServiceClassName);
            return (EmailService) emailServiceClass.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load email service", ex);
        }
    }

}
