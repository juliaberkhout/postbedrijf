package data;

public class Adres extends Locatie
{
	private String straat;
	
	private String huisnummer;
	
	private String postcode;
	
	private String plaats;

	public Adres(String straat, String huisnummer, String postcode, String plaats) {
		this.straat = straat;
		this.huisnummer = huisnummer;
		this.postcode = postcode;
		this.plaats = plaats;
	}
	
	@Override
	public String toString() {
		return straat + ", " + huisnummer + ", " + postcode + ", " + plaats;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	@Override
	public String getOmschrijving() {
		return "Het pakket bevindt zich op " + straat + " " + huisnummer;
	}
	
}
