package services;

import java.time.LocalDateTime;

import dao.PakketDAO;
import data.Locatie;
import data.Pakket;
import data.PakketStatus;
import data.Rit;
import data.Sorteercentrale;

public class BezorgService {
	
	private PakketDAO dao;
	private EmailService emailService;
	
	public BezorgService(EmailLogica emailLogica)
	{
		this.dao = new PakketDAO();
		this.emailService = new EmailService(emailLogica);
	}

	public void haalPakketAf(long code, Sorteercentrale centrale)
	{
		Pakket pakket = dao.get(code);
		pakket.setLocatie(centrale);
		pakket.setStatus(PakketStatus.OPGEHAALD);
		dao.save(pakket);
	}
	
	public void bezorgPakket(long code, LocalDateTime datumTijd)
	{
		Pakket pakket = dao.get(code);

		Locatie locatie = pakket.getLocatie();
		if (!(locatie instanceof Sorteercentrale))
			throw new UnsupportedOperationException("Het pakket moet eerst bij de klant opgehaald worden!");
		Rit rit = new Rit(datumTijd, (Sorteercentrale) locatie);
		pakket.setLocatie(rit);
		pakket.setStatus(PakketStatus.ONDERWEG);
		dao.save(pakket);
	}
	
	public void leverPakketAf(long code)
	{
		Pakket pakket = dao.get(code);
		pakket.setLocatie(pakket.getOntvanger().getAdres());
		pakket.setStatus(PakketStatus.AFGELEVERD);
		emailService.sendBezorgEmail(pakket);
		dao.save(pakket);
	}
	
}
