package data;

import dao.KlantDAO;

public class Klant {
	
	private String naam;
	
	private Adres adres;
	
	private String emailadres;
	
	private Long id;
	
	private Klant(long id, String naam, Adres adres, String emailadres) {
		this.id = id;
		this.naam = naam;
		this.adres = adres;
		this.emailadres = emailadres;
	}

	public String getNaam() {
		return naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Klant create(String naam, Adres adres, String emailadres)
	{
		Klant klant = new Klant(KlantDAO.klanten.size()+1, naam, adres, emailadres);
		return klant;
	}
	
}
