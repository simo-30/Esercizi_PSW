package applicazione.barca;

import _framework.Task;
import _gestioneeventi.Environment;
import _gestioneeventi.Evento;
import applicazione.eventi.Immersione;
import applicazione.eventi.InvioImmersione;

class BarcaFired implements Task{

	private boolean eseguita = false;
	
	Barca b;
	Evento e;
	
	public BarcaFired(Barca b, Evento e) {
		this.b = b;
		this.e = e;
	}
	
	@Override
	public synchronized void esegui() {
		if (eseguita || (e.getDestinatario() != b && e.getDestinatario() != null))
			return;
		eseguita = true;	
		switch(b.getStato()) {
		case PRONTO:
			if(e.getClass() == InvioImmersione.class) {
				InvioImmersione ii = (InvioImmersione)e;
				Environment.aggiungiEvento(new Immersione(b, ii.sottomarino, ii.profondita));
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
