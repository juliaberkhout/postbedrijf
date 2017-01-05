package data;

import dao.PakketDAO;

public class Pakket {

	private Klant afzender;
	
	private Klant ontvanger;
	
	private int gewicht;
	
	private long code;
	
	private Locatie locatie;
	
	private PakketStatus status;

	private Pakket(Klant afzender, Klant ontvanger, int gewicht, Locatie locatie, PakketStatus status, long code) {
		super();
		this.afzender = afzender;
		this.ontvanger = ontvanger;
		this.gewicht = gewicht;
		this.locatie = locatie;
		this.status = status;
		this.setCode(code);
	}
	
	public static Pakket create(Klant afzender, Klant ontvanger, int gewicht, Locatie locatie, PakketStatus status)
	{
		Pakket pakket = new Pakket(afzender, ontvanger, gewicht, locatie, status, PakketDAO.pakketten.size()+1);
		return pakket;
	}

	public Klant getAfzender() {
		return afzender;
	}

	public Klant getOntvanger() {
		return ontvanger;
	}

	public int getGewicht() {
		return gewicht;
	}


	public Locatie getLocatie() {
		return locatie;
	}

	public void setAfzender(Klant afzender) {
		this.afzender = afzender;
	}

	public void setOntvanger(Klant ontvanger) {
		this.ontvanger = ontvanger;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}


	public void setLocatie(Locatie locatie) {
		this.locatie = locatie;
	}

	public PakketStatus getStatus() {
		return status;
	}

	public void setStatus(PakketStatus status) {
		this.status = status;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}
	
	
}
