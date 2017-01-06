package data;

public enum PakketStatus {
	
	AANGEMELD("Uw pakket is aangemeld en wordt spoedig opgehaald."),
	OPGEHAALD("Uw pakket is opgehaald en onderweg naar de sorteercentrale."),
	ONDERWEG("Uw pakket is verstuurd vanaf de centrale en onderweg."),
	AFGELEVERD("Uw pakket is bij u bezorgd.");
	
	private String omschrijving;
	
	private PakketStatus(String omschrijving)
	{
		this.omschrijving = omschrijving;
	}
	
	public String getOmschrijving()
	{
		return omschrijving;
	}
	
	@Override
	public String toString() {
		return getOmschrijving();
	}
}
