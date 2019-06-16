package main;

import applicazione.TipoLinkContiene;
import applicazione.TipoLinkInclude;
import applicazione.barca.Barca;
import applicazione.batiscafo.Batiscafo;
import applicazione.esplorazione.Esplorazione;
import applicazione.sottomarino.Sottomarino;
import applicazione.zonamare.ZonaMare;
import attivita.complesse.AttivitaPrincipale;

public class Main {

	public static void main(String[] args) {
		Esplorazione esplorazione = createTest();		
		Thread attivitaPrincipale = new Thread(new AttivitaPrincipale(esplorazione));
		attivitaPrincipale.start();
		try {
			attivitaPrincipale.join();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Esplorazione createTest() {
		Esplorazione esplorazione = new Esplorazione("Ventimila leghe sotto i mari");
		ZonaMare zm = new ZonaMare(11.35, 142.2, "Fossa delle Marianne");
		esplorazione.inserisciRiguarda(zm);
		
		Barca b1 = new Barca("Capitano", 120);
		
		Sottomarino s11 = new Sottomarino("Nautilus", 80);
		Batiscafo ba111 = new Batiscafo("Serv01", 1000.);
		Batiscafo ba112 = new Batiscafo("Serv02", 2000.);
		TipoLinkInclude tli111 = new TipoLinkInclude(s11, ba111);
		TipoLinkInclude tli112 = new TipoLinkInclude(s11, ba112);
		s11.inserisciLinkInclude(tli111);
		s11.inserisciLinkInclude(tli112);
		TipoLinkContiene tlc11 = new TipoLinkContiene(b1, s11);
		b1.inserisciLinkContiene(tlc11);
		
		
		Sottomarino s12 = new Sottomarino("Atlantide", 50);
		Batiscafo ba121 = new Batiscafo("Serv01", 1000.);
		Batiscafo ba122 = new Batiscafo("Serv02", 2000.);
		TipoLinkInclude tli121 = new TipoLinkInclude(s12, ba121);
		TipoLinkInclude tli122 = new TipoLinkInclude(s12, ba122);
		s12.inserisciLinkInclude(tli121);
		s12.inserisciLinkInclude(tli122);
		TipoLinkContiene tlc12 = new TipoLinkContiene(b1, s12);
		b1.inserisciLinkContiene(tlc12);
		
		esplorazione.inserisciAssociato(b1);
		esplorazione.inserisciAssociato(s11);
		esplorazione.inserisciAssociato(ba111);
		esplorazione.inserisciAssociato(ba112);
		esplorazione.inserisciAssociato(s12);
		esplorazione.inserisciAssociato(ba121);
		esplorazione.inserisciAssociato(ba122);
		
		Barca b2 = new Barca("Dakkar", 150);
		
		Sottomarino s21 = new Sottomarino("Geonosis", 30);
		Batiscafo ba211 = new Batiscafo("Serv01", 1000.);
		Batiscafo ba212 = new Batiscafo("Serv02", 2000.);
		TipoLinkInclude tli211 = new TipoLinkInclude(s21, ba211);
		TipoLinkInclude tli212 = new TipoLinkInclude(s21, ba212);
		s21.inserisciLinkInclude(tli211);
		s21.inserisciLinkInclude(tli212);
		TipoLinkContiene tlc21 = new TipoLinkContiene(b2, s21);
		b2.inserisciLinkContiene(tlc21);
		
		
		Sottomarino s22 = new Sottomarino("Perla", 10);
		Batiscafo ba221 = new Batiscafo("Serv01", 1000.);
		Batiscafo ba222 = new Batiscafo("Serv02", 2000.);
		TipoLinkInclude tli221 = new TipoLinkInclude(s22, ba221);
		TipoLinkInclude tli222 = new TipoLinkInclude(s22, ba222);
		s22.inserisciLinkInclude(tli221);
		s22.inserisciLinkInclude(tli222);
		TipoLinkContiene tlc22 = new TipoLinkContiene(b2, s22);
		b2.inserisciLinkContiene(tlc22);
		
		esplorazione.inserisciAssociato(b2);
		esplorazione.inserisciAssociato(s21);
		esplorazione.inserisciAssociato(ba211);
		esplorazione.inserisciAssociato(ba212);
		esplorazione.inserisciAssociato(s22);
		esplorazione.inserisciAssociato(ba221);
		esplorazione.inserisciAssociato(ba222);
		
		return esplorazione;
	}
}
