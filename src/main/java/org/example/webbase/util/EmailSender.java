package org.example.webbase.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import static org.example.webbase.constant.PagesConstants.EMAIL_USERNAME;
import static org.example.webbase.constant.PagesConstants.EMAIL_PASSWORD;

public class EmailSender {

    private static volatile EmailSender instance;
    private static final String USERNAME = EMAIL_USERNAME;
    private static final String PASSWORD = EMAIL_PASSWORD;
    private Properties mailProps = new Properties();




    private EmailSender() {
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.port", "587");
        mailProps.put("mail.smtp.starttls.enable", "true");
    }
    public static EmailSender getInstance(){
        if (instance == null) {
            synchronized (EmailSender.class) {
                if (instance == null) {
                    instance = new EmailSender();
                }
            }
        }
        return instance;
    }

    public void sendEmail(String receiver, int verificationCode) {
        Session session = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Verification code");
            message.setText("Verification code: " + verificationCode);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
