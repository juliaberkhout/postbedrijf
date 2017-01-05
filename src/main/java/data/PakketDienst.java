//package data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class PakketDienst {
//
//	public static List<Pakket> pakketten = new ArrayList<>();
//	
//	public static Scanner in = new Scanner(System.in);
//
//	public static void main(String[] args) {
//		while (true) {
//			Scanner in = new Scanner(System.in);
//			System.out.println("Wat wilt u doen?");
//			System.out.println("- maak adres aan");
//			System.out.println("- quit");
//			System.out.println("- meld pakket aan");
//			String userinput = in.nextLine();
//			
//			if (userinput.equals("maak adres aan")) {
//				Adres adres = AanmeldService.createAdres();
//				adressen.add(adres);
//				System.out.println(adres);
//				System.out.println(adressen);
//			}
//			else if (userinput.equals("quit"))
//			{
//				in.close();
//				System.exit(0);
//			}
//		}
//	}
//	
//	public static List<Pakket> getPaketten()
//	{
//		return pakketten;
//	}
//}
