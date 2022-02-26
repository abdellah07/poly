package com.polyfrontiere.data.mail;

import com.polyfrontiere.data.declaration.domain.Declaration;
import com.polyfrontiere.data.utils.FileSystemPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootApplication
@Controller
public class EmailSender {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendEmail() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        // Create a Simple MailMessage.
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo("najiabdo92@gmail.com");
        mimeMessageHelper.setSubject("Test Email (QR CODE)");
        mimeMessageHelper.setText("<html>" +
                "<body>" +
                "<div>" +
                "<img src=\"https://c.tenor.com/Z80dyA5QOiIAAAAC/dance-potato.gif\"" +
                "titre.png\">"+"</div>" +
                "<h1>" +
                "ceci est un mail de test" +
                "</h1> " +
                "</body>" +
                "</html>", true);

        // String path = "C:\\Users\\najia\\OneDrive\\Bureau\\ps7-21-22-al-iotcps-21_22\\polyfrontiere\\src\\main\\java\\com\\polyfrontiere\\polyfrontiere\\mail\\print1.jpg";

        String relPath = FileSystemPaths.PATH_QR_IMAGES.getPath()+ "fingerprints/print1.jpg";
        File file = new File(relPath);
        FileSystemResource file1 = new FileSystemResource(new File(file.getAbsolutePath()));
        mimeMessageHelper.addAttachment("QrCode.jpg", file1);

        this.emailSender.send(message);

        return "Email Sent!";

    }
    public boolean sendEmail(Declaration declaration, File qrFile) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        // Create a Simple MailMessage.
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo(declaration.getConducteur().getMail());
        mimeMessageHelper.setSubject("PolyFrontiere (QR CODE) : Do not reply");
        mimeMessageHelper.setText("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        .photo{\n" +
                "            display: flex;\n" +
                "            justify-content: space-around;\n" +
                "        }\n" +
                "        span{\n" +
                "            text-transform: uppercase;" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"photo\">\n" +
                "        <img src=\"https://i.postimg.cc/MKLx9KBs/Poly-frontiere.gif\" alt=\"\">\n" +
                "    </div>\n" +
                "    <div class=\"photo\" style=\"font-size: larger;\">\n" +
                "        <div>\n" +
                "            <div>Bonjour "+declaration.getConducteur().getPrenom()+" "+declaration.getConducteur().getNom()+"</span>,</div>\n" +
                "            <div>You will find attached your Qr code containing your application file.</div>\n" +
                "            <div style=\"font-weight: bolder;\">Please be accompanied by your QR CODE when crossing the border.</div>\n" +
                "            <div style=\"font-weight: bolder;color:red;\">This email was generated automatically please do not reply.</div>\n" +
                "            <div>cordially</div>\n" +
                "            <div>PolyFrontiere support</div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>",true);

        FileSystemResource file1 = new FileSystemResource(qrFile.getAbsolutePath());
        mimeMessageHelper.addAttachment("QrCode.jpg", file1);

        this.emailSender.send(message);

        return true;
    }
}
