package data;

import java.io.Serializable;

public class KlantDummy implements Serializable {

	private static final long serialVersionUID = 1L;

	private String naam;

	private String straat;

	private String huisnummer;

	private String postcode;

	private String plaats;

	private String emailadres;

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
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
}
