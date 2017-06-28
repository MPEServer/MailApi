package tech.teslex.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * ------------------------------
 * Created by expexes on 28.06.2017.
 * Site: teslex.tech
 * -------------------------------
 */
public class Mail {

    private static Properties props = new Properties();
    private static Session session;

    private String username;
    private String password;

    public Mail(final String username, final String password) {
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        this.username = username;
        this.password = password;

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public Mail(Properties properties, final String username, final String password) {
        props = properties;
        this.username = username;
        this.password = password;

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public Mail(Properties properties) {
        props = properties;
        session = Session.getDefaultInstance(props);
    }

    public void send(String from, String to, String title, String text, String type) throws MessagingException {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(title);
            message.setContent(text, type);

            Transport.send(message);

    }
}
