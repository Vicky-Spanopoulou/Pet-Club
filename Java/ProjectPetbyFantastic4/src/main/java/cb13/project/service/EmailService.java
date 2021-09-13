package cb13.project.service;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static cb13.project.constant.EmailConstant.*;
import static cb13.project.constant.emailbuilder.BuildBlockUserEmail.blockedUserEmail;
import static cb13.project.constant.emailbuilder.BuildNewsLetterEmail.newsLetterUserEmail;
import static cb13.project.constant.emailbuilder.BuildPageEmail.pageMail;
import static cb13.project.constant.emailbuilder.BuildPasswordEmail.buildResetChangePasswordEmail;
import static cb13.project.constant.emailbuilder.BuildTransactionMail.transactionMail;
import static cb13.project.constant.emailbuilder.BuildVerificationEmail.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import javax.mail.internet.AddressException;

@Service
public class EmailService {

    public void sendPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createPasswordEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    public void sendVerificationEmail(String firstName, String password, String email, String verifiedCode) throws MessagingException {
        Message message = createVerificationEmail(firstName, password, email, verifiedCode);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }


    private Message createPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setContent(buildResetChangePasswordEmail(firstName, password), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Message createVerificationEmail(String firstName, String password, String email, String verifiedCode) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setContent(verificationEmail(firstName, verifiedCode), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }


    public void createPageEmail(String emailSender, String emailSubject, String emailContent) throws AddressException, MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(emailSender, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(emailSubject);
        message.setContent(pageMail(emailSender, emailContent), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    public void createNewsLetterEmail( String emailContent) throws AddressException, MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(FROM_EMAIL, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject("Write me to News letter");
        message.setContent(newsLetterUserEmail(emailContent), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }


    public void createUserBlockedEmail(String emailSender, String emailSubject) throws AddressException, MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(emailSender, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(emailSubject);
        message.setContent(blockedUserEmail(), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }



    public void createTransactionEmail(String emailSender,
                                       Date date,
                                       String companyName,
                                       String address,
                                       Long id,
                                       String phone,
                                       String type,
                                       String email,
                                       String financialService,
                                       String vatNumber,
                                       String paid,
                                       String sub_name,
                                       String sub_price,
                                       int sub_duration,
                                       String sub_role,
                                       boolean advForEver) throws AddressException, MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(emailSender, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject("Pet Club -Transaction");
        message.setContent(transactionMail(date, companyName, address, id, phone, type, email, financialService, vatNumber, paid,
                sub_name, sub_price, sub_duration, sub_role, advForEver), "text/html");
        message.setSentDate(new Date());
        message.saveChanges();
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }


}
