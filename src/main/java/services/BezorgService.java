package services;

import static data.PakketStatus.AANGEMELD;
import static data.PakketStatus.AFGELEVERD;
import static data.PakketStatus.ONDERWEG;
import static data.PakketStatus.OPGEHAALD;

import java.time.LocalDateTime;

import dao.PakketDAO;
import data.Adres;
import data.Pakket;
import data.PakketStatus;
import data.Rit;
import data.Sorteercentrale;

public class BezorgService {
	
	private PakketDAO dao;
	private EmailService emailService;
	private Sorteercentrale sorteercentrale = new Sorteercentrale(new Adres("teststraat", "1", "7424TG", "Teststad"), "1234");
	
	public BezorgService(EmailLogica emailLogica)
	{
		this.dao = new PakketDAO();
		this.emailService = new EmailService(emailLogica);
	}

	public String haalPakketAf(long code)
	{
		Pakket pakket = dao.get(code);
		if (pakket == null)
			return "Pakket onbekend.";
		if (!pakket.getStatus().equals(AANGEMELD))
			return "Het pakket is al afgehaald.";
		pakket.setLocatie(sorteercentrale);
		pakket.setStatus(OPGEHAALD);
		dao.save(pakket);
		return "Pakket is afgehaald";
		
	}
	
	public String bezorgPakket(long code, LocalDateTime datumTijd)
	{
		Pakket pakket = dao.get(code);
		if (pakket == null)
			return "Pakket onbekend.";
		if (!pakket.getStatus().equals(OPGEHAALD))
			return "Het pakket moet eerst bij de klant opgehaald worden!";
		Rit rit = new Rit(datumTijd, (Sorteercentrale) pakket.getLocatie());
		pakket.setLocatie(rit);
		pakket.setStatus(ONDERWEG);
		dao.save(pakket);
		return "Pakket wordt bezorgd";
	}
	
	public String leverPakketAf(long code)
	{
		Pakket pakket = dao.get(code);
		if (pakket == null)
			return "Pakket onbekend.";
		
		PakketStatus status = pakket.getStatus();
		if (!status.equals(ONDERWEG))
			if (status.equals(AANGEMELD))
				return "Het pakket moet eerst worden afgehaald.";
			else if (status.equals(OPGEHAALD))
				return "Het pakket bevindt zich nog op de sorteercentrale.";
		pakket.setLocatie(pakket.getOntvanger().getAdres());
		pakket.setStatus(AFGELEVERD);
		emailService.sendBezorgEmail(pakket);
		dao.save(pakket);
		return "Het pakket is afgeleverd.";
	}
	
	public String trackPakket(long code)
	{
		Pakket pakket = dao.get(code);
		if (pakket == null)
			return "Pakket onbekend";
		return pakket.getStatus().getOmschrijving();
	}
	
}
