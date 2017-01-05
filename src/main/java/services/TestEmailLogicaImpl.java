package services;

import org.simplejavamail.email.Email;

public class TestEmailLogicaImpl implements EmailLogica {

	@Override
	public void sendEmail(Email email) {
		System.out.println("Mailfunctionaliteit is aangeroepen voor email naar " + email.getRecipients() + " met onderwerp: " + email.getSubject());
	}

}
