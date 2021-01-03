package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	
	@Async
	public void sendSimpleMessage(String to, String subject, String text)  throws MailException, InterruptedException  {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		System.out.println("poslao");
	}
}
