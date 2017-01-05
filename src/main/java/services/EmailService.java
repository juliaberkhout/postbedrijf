package services;

import javax.mail.Message.RecipientType;

import org.simplejavamail.email.Email;

import data.Klant;
import data.Pakket;

public class EmailService {
	
	private EmailLogica emailLogica;

	public EmailService(EmailLogica emailLogica)
	{
		this.emailLogica = emailLogica;
	}
	
	public Email createAanmeldMail(Klant afzender, long pakketCode)
	{
		Email email = new Email();
		email.setFromAddress("Julia's postbedrijf", "noreply@juliaspostbedrijf.nl");
		email.addRecipient(afzender.getNaam(), afzender.getEmailadres(), RecipientType.TO);
		email.setSubject("Uw pakket is aangemeld");
		email.setText("Uw pakket is aangemeld. U kunt uw pakket volgen met de code" + " " + pakketCode);
		return email;
		
	}
	
	public Email createBezorgEmail(Klant afzender, Klant ontvanger)
	{
		Email email = new Email();
		email.setFromAddress("Julia's postbedrijf", "noreply@juliaspostbedrijf.nl");
		email.addRecipient(afzender.getNaam(), afzender.getEmailadres(), RecipientType.TO);
		email.setSubject("Uw pakket is bezorgd");
		email.setText("Uw pakket is bezorgd bij " + ontvanger.getNaam() + " op " + ontvanger.getAdres());
		return email;
	}
	
	public void sendAanmeldEmail(Pakket pakket)
	{
		emailLogica.sendEmail(createAanmeldMail(pakket.getAfzender(), pakket.getCode()));
	}
	
	public void sendBezorgEmail(Pakket pakket)
	{
		emailLogica.sendEmail(createBezorgEmail(pakket.getAfzender(), pakket.getOntvanger()));
	}
}
