package dao;

import java.util.HashMap;

import data.Pakket;

public class PakketDAO {
	
	public static HashMap<Long, Pakket> pakketten = new HashMap<>();

	public Pakket get(long code)
	{
		Pakket pakket = pakketten.get(code);
		return pakket;
	}
	
	public void save(Pakket pakket)
	{
		pakketten.put(pakket.getCode(), pakket);
	}

}
