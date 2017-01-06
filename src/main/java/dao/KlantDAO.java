package dao;

import java.util.HashMap;
import java.util.Optional;

import data.Klant;

public class KlantDAO {
	
	public static HashMap<Long, Klant> klanten = new HashMap<>();
	
	public Klant get(long id)
	{
		Klant klant = klanten.get(id);
		return klant;
	}
	
	public Optional<Klant> getByName(String name)
	{
		return klanten.values().stream().filter(k -> k.getNaam().equals(name)).findFirst();
	}
	
	public void save(Klant klant)
	{
		klanten.put(klant.getId(), klant);
	}

}
