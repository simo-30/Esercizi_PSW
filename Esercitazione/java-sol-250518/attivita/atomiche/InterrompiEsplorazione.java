package attivita.atomiche;

import java.util.Set;

import _framework.Task;
import _gestioneeventi.Environment;
import applicazione.TipoLinkContiene;
import applicazione.barca.Barca;
import applicazione.dispositivo.Dispositivo;
import applicazione.esplorazione.Esplorazione;
import applicazione.eventi.Risalita;

public class InterrompiEsplorazione implements Task {

	private boolean eseguita = false;
	
	private Esplorazione esp;
	
	public InterrompiEsplorazione(Esplorazione esplorazione) {
		this.esp = esplorazione;
	}
	
	@Override
	public synchronized void esegui() {
		if(eseguita)
			return;
		eseguita = true;
		
		Set<Dispositivo> barche = esp.getAssociato();
		for(Dispositivo d : barche) {
			if(d.getClass() == Barca.class) {
				Barca curB = (Barca)d;
				Set<TipoLinkContiene> tlcs = curB.getLinkContiene();
				for(TipoLinkContiene tlc : tlcs) {
					Environment.aggiungiEvento(new Risalita(curB, tlc.getSottomarino()));
				}
			}
		}
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
