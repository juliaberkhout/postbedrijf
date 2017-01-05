package services;

import dao.KlantDAO;
import dao.PakketDAO;
import data.Adres;
import data.Klant;
import data.KlantDummy;
import data.Pakket;
import data.PakketStatus;

public class KlantService {
	

	private EmailService emailService;

	public KlantService(EmailLogica emailLogica)
	{
		this.emailService = new EmailService(emailLogica);
	}
	
	public Klant createAndSaveKlant(String naam, String straat, String huisnummer, String postcode, String plaats, String emailadres)
	{
		Klant nieuweKlant = Klant.create(naam, new Adres(straat, huisnummer, postcode, plaats), emailadres);
		KlantDAO dao = new KlantDAO();
		dao.save(nieuweKlant);
		return nieuweKlant;
	}
	
	public long meldPakketAan(Klant afzender, Klant ontvanger, int gewicht)
	{
		Pakket pakket = Pakket.create(afzender, ontvanger, gewicht, afzender.getAdres(), PakketStatus.AANGEMELD);
		PakketDAO dao = new PakketDAO();
		dao.save(pakket);
		emailService.sendAanmeldEmail(pakket);
		return pakket.getCode();
	}
	
	public String trackPakket(long code)
	{
		PakketDAO dao = new PakketDAO();
		Pakket pakket = dao.get(code);
		return pakket.getStatus().getOmschrijving();
	}
	
	public long aanmelden(KlantDummy dummyAfzender, KlantDummy dummyOntvanger, int gewicht)
	{
		Klant afzender = createAndSaveKlant(dummyAfzender.getNaam(), dummyAfzender.getStraat(), dummyAfzender.getHuisnummer(), dummyAfzender.getPostcode(), dummyAfzender.getPlaats(), dummyAfzender.getEmailadres());
		Klant ontvanger = createAndSaveKlant(dummyOntvanger.getNaam(), dummyOntvanger.getStraat(), dummyOntvanger.getHuisnummer(), dummyOntvanger.getPostcode(), dummyOntvanger.getPlaats(), dummyOntvanger.getEmailadres());
		return meldPakketAan(afzender, ontvanger, gewicht);
	}
	

}
