package data;

public class Sorteercentrale extends Locatie
{
	private Adres adres;
	
	private String code;
	
	public Sorteercentrale(Adres adres, String code) {
		this.adres = adres;
		this.code = code;
	}
	
	@Override
	public String getOmschrijving() {
		return "Uw pakket is bij sorteercentrale" + code + ", " + adres;
	}
	
}
