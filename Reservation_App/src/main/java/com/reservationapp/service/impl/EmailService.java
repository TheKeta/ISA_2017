package com.reservationapp.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.reservationapp.model.User;


//@Service
public class EmailService {

//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	/*
//	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
//	 */
//	@Autowired
//	private Environment env;
//
//	/*
//	 * Anotacija za oznacavanje asinhronog zadatka
//	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
//	 */
//	@Async
//	public void sendNotificaitionAsync(User user, String appUrl) throws MailException, InterruptedException {
//
//		//Simulacija duze aktivnosti da bi se uocila razlika
//		Thread.sleep(10000);
//		System.out.println("Slanje emaila...");
//
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(user.getEmail());
//		mail.setFrom(env.getProperty("spring.mail.username"));
//		mail.setSubject("Registration Confirmation");
//		mail.setText("To confirm your e-mail address, please click the link below:\n" + appUrl + "/confirm?token=" + user.getToken());
//		javaMailSender.send(mail);
//
//		System.out.println("Email poslat!");
//	}

	
//  @Autowired
//  private JavaMailSender mailSender;
  
//  @Bean
//  public JavaMailSender mailSender() {
//      JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//
//      
//      javaMailSender.setUsername("sdmrbg123@gmail.com");
//      javaMailSender.setPassword("bezlosmija");
//      
//
//      Properties properties = new Properties();
//      properties.setProperty("mail.transport.protocol", "smtp");
//      properties.setProperty("mail.smtp.auth", Boolean.toString(true));
//      properties.setProperty("mail.smtp.starttls.enable", Boolean.toString(true));
//     // properties.setProperty("mail.debug", Boolean.toString(this.debug));
//      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//      properties.setProperty("mail.smtp.port", Integer.toString(587));
//      properties.setProperty("mail.smtp.ssl.trust", Boolean.toString(true));
//      javaMailSender.setJavaMailProperties(properties);
//
//      return javaMailSender;
//  }

//  
//  @Async
//  public void sendEmail(SimpleMailMessage email) {
//    mailSender.send(email);
//  }
}
