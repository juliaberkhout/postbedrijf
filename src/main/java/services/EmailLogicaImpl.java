package services;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;

public class EmailLogicaImpl implements EmailLogica
{
	@Override
	public void sendEmail(Email email) {
		new Mailer("mail.topicus.nl", 25, null, null).sendMail(email);
	}

}
