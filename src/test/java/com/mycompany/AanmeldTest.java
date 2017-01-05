package com.mycompany;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import dao.KlantDAO;
import dao.PakketDAO;
import data.Adres;
import data.Klant;
import data.PakketStatus;
import data.Sorteercentrale;
import services.BezorgService;
import services.KlantService;
import services.TestEmailLogicaImpl;

public class AanmeldTest {
	
	private KlantDAO klantDAO;
	private PakketDAO pakketDAO;
	private BezorgService bezorgService;
	private KlantService klantService;
	private Klant julia;
	private Klant olaf;
	private Sorteercentrale sorteercentrale;

	@Before
	public void before()
	{
		this.klantDAO = new KlantDAO();
		this.pakketDAO = new PakketDAO();
		this.klantService = new KlantService(new TestEmailLogicaImpl());
		this.bezorgService = new BezorgService(new TestEmailLogicaImpl());
		
		julia = klantService.createAndSaveKlant("Julia", "Parsivalstraat", "12", "7323 TG", "Apeldoorn", "julia.berkhout@gmail.com");
		olaf = klantService.createAndSaveKlant("Olaf", "Dovenetel", "110", "7322DV", "Apeldoorn", "olaf.orizande@gmail.com");
		
		this.sorteercentrale = new Sorteercentrale(new Adres("Melkweg", "1", "7335 JS", "Nepstad"), "melkweg1234");
	}
	
	@Test
	public void aanmeldTest()
	{
		long code = klantService.meldPakketAan(julia, olaf, 10);
		assertEquals(PakketStatus.AANGEMELD, pakketDAO.get(code).getStatus());
	}
	
	@Test
	public void trackAndTraceTest()
	{
		long code = klantService.meldPakketAan(julia, olaf, 10);
		String melding = klantService.trackPakket(code);
		assertEquals("Uw pakket is aangemeld en wordt spoedig opgehaald", melding);
		bezorgService.haalPakketAf(code, sorteercentrale);
		assertEquals(PakketStatus.OPGEHAALD, pakketDAO.get(code).getStatus());
	}
	
	@Test
	public void bezorgTest()
	{
		long code = klantService.meldPakketAan(julia, olaf, 10);
		bezorgService.haalPakketAf(code, sorteercentrale);
		bezorgService.bezorgPakket(code, LocalDateTime.now());
		assertEquals(PakketStatus.ONDERWEG, pakketDAO.get(code).getStatus());
	}
	
	@Test
	public void afleverTest()
	{
		long code = klantService.meldPakketAan(julia, olaf, 10);
		bezorgService.leverPakketAf(code);
		
	}
	

}
