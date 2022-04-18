package com.example.vpndatabase;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail
{

    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL

    Session newSession = null;
    MimeMessage mimeMessage = null;
    public static void main(String args[]) throws AddressException, MessagingException, IOException
    {
        /*Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();*/
    }

    public void sendEmail() throws MessagingException {
        String fromUser = "faiyazandsajidsqr@gmail.com";  //Enter sender email id
        String fromUserPassword = "admin  admin";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    public MimeMessage draftEmail(String emailReceipients) throws AddressException, MessagingException, IOException {

        String emailSubject = "Verify your account.";
        String emailBody ="Verify your account<br>";
        String url = "https://www.youtube.com/watch?v=YYVlK14FEWM";
        String cont="<a href='"+url+"'>"+"click here</a>";
        mimeMessage = new MimeMessage(newSession);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients));

        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE
        // CREATE MESSAGE BODY PARTS
        // CREATE MESSAGE MULTIPART
        // ADD MESSAGE BODY PARTS ----> MULTIPART
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object


        /*MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(emailBody, "utf-8");

        bodyPart.setContent(emailBody,"html/text; charset=utf-8");

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);*/
        mimeMessage.setContent(emailBody+cont,"text/html;charset=utf-8");
        return mimeMessage;
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}