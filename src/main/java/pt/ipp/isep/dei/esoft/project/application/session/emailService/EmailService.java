package pt.ipp.isep.dei.esoft.project.application.session.emailService;

/**
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send email.
     *
     * @param name the name
     * @param body the body
     */
    void sendEmail(String name, String body);
}
