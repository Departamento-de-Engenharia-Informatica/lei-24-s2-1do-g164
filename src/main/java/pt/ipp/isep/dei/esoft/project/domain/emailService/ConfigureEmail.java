package pt.ipp.isep.dei.esoft.project.domain.emailService;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void updateConfigFile(String key, String value) {
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(CONFIG_FILE);
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(CONFIG_FILE);
            props.setProperty(key, value);
            props.store(out, "");
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to update configuration file", ex);
        }

    }

}
