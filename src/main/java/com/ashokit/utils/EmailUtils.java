package com.ashokit.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendMailWithAttachment(String subject,String body,String to,File file)
	{
		// Creating a mime message
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			 // Setting multipart as true for attachments to be send
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            
          //set the email details like body,to address,etc using mimemessagehelper obj
          helper.setSubject(subject);
          helper.setText(body, true);
          helper.setTo(to);
          helper.addAttachment("Plans-Information", file);
          
       // Sending the mail
          mailSender.send(mimeMessage);
          
          file.delete();
          
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
