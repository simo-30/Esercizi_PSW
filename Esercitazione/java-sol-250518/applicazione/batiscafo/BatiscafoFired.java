package applicazione.batiscafo;

import _framework.Task;
import _gestioneeventi.Environment;
import _gestioneeventi.Evento;
import applicazione.batiscafo.Batiscafo.Stato;
import applicazione.eventi.Registra;
import applicazione.eventi.RientroEffettuato;
import applicazione.eventi.Risalita;
import applicazione.sottomarino.Sottomarino;

class BatiscafoFired implements Task {

	private Batiscafo b;
	private Evento e;
	
	private boolean eseguita = false;
	
	public BatiscafoFired(Batiscafo b, Evento e) {
		this.b = b;
		this.e = e;
	}

	public synchronized void esegui() {
		if (eseguita || (e.getDestinatario() != b && e.getDestinatario() != null))
			return;
		eseguita = true;
		switch (b.getStato()) {
		case PRONTO:
			if (e.getClass() == Registra.class) {
				Registra reg = ((Registra) e);
				b.profonditaAttuale = reg.profondita; // simula immersione
				System.out.println("Il batiscafo " + b.getNome() + " si trova a " + ((Sottomarino)(e.getMittente())).getProfonditaMax() + " e si immerge a -> " + reg.profondita);
				b.statoattuale = Stato.REGISTRAZIONE;
			}
			break;
		case REGISTRAZIONE:
			if (e.getClass() == Risalita.class) {
				b.profonditaAttuale = 0; // simula ritorno al sottomarino
				b.statoattuale = Stato.PRONTO;
				System.out.println("Il batiscafo " + b.getNome() + " torna dal suo sottomarino " + ((Sottomarino)(e.getMittente())).getNome());
				Environment.aggiungiEvento(new RientroEffettuato(b, b.getLinkInclude().getSottomarino()));
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
