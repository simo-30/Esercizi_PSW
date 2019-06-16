package applicazione.sottomarino;

import _framework.Task;
import _gestioneeventi.Environment;
import _gestioneeventi.Evento;
import applicazione.batiscafo.Batiscafo;
import applicazione.eventi.Immersione;
import applicazione.eventi.Registra;
import applicazione.eventi.RientroEffettuato;
import applicazione.eventi.Risalita;
import applicazione.sottomarino.Sottomarino.Stato;

class SottomarinoFired implements Task {
	
	private Sottomarino s;
	private Evento e;
	
	private boolean eseguita = false;
	
	public SottomarinoFired(Sottomarino s, Evento e) {
		this.s = s;
		this.e = e;
	}
	
	public synchronized void esegui() {
		if (eseguita || (e.getDestinatario() != s && e.getDestinatario() != null))
			return;
		eseguita = true;
		switch (s.getStato()) {
		case A_BORDO:
			if (e.getClass() == Immersione.class) {
				Immersione imm = ((Immersione) e);
				if(e.getMittente().equals(s.getLinkContiene().getBarca())) {
					// Caso profondita' indicata <= profondita' consentita
					if(imm.profondita <= s.profonditaMax) {
						System.out.println("Il sottomarino " + s.getNome() + " si trova a " + s.profonditaAttuale
								+ " e si immerge a -> " + imm.profondita + " in registrazione autonoma");
						s.profonditaAttuale = imm.profondita; // simula immersione alla profondita' indicata
						s.statocorrente = Stato.REG_AUTONOMA;
					}
					// Caso profondita' indicata > profondita' consentita
					else {
						System.out.println("Il sottomarino " + s.getNome() + " si trova a " + s.profonditaAttuale
								+ " e si immerge a -> " + imm.profondita + " in registrazione con batiscafo");
						s.profonditaAttuale = s.profonditaMax; // simula immersione alla profondita' massima
						s.statocorrente = Stato.REG_BATISCAFO;
						s.selezionato = s.getLinkInclude().iterator().next().getBatiscafo(); // simula scelta del batiscafo tra quelli disponibili
						s.selezionato.setProfonditaAttuale(s.profonditaMax);
						Environment.aggiungiEvento(new Registra(s, s.selezionato, imm.profondita));
					}
				}
			}
			break;
		case REG_AUTONOMA:
			if (e.getClass() == Risalita.class) {
				System.out.println("Il sottomarino " + s.getNome() + " riemerge");
				s.profonditaAttuale = 0; // simula emersione
				s.statocorrente = Stato.A_BORDO;
			}
			break;
		case REG_BATISCAFO:
			if (e.getClass() == Risalita.class) {
				System.out.println("Il sottomarino " + s.getNome() + " ordina al batiscafo " + s.selezionato.getNome() + " di risalire");
				Environment.aggiungiEvento(new Risalita(s, s.selezionato));
				s.statocorrente = Stato.ATTENDI_BATISCAFO;
			}
			break;
		case ATTENDI_BATISCAFO:
			if(e.getClass() == RientroEffettuato.class) {
				if(e.getMittente().equals(s.selezionato)) {
					System.out.println("Il sottomarino " + s.getNome() + " riemerge con il suo batiscafo " + ((Batiscafo)e.getMittente()).getNome());
					s.profonditaAttuale = 0; // simula emersione
					s.statocorrente = Stato.A_BORDO;
				}
			}
			break;			
		default:
			throw new RuntimeException("Stato corrente non riconosciuto.");
		}
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
