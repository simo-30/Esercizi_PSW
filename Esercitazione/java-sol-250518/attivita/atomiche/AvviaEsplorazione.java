package attivita.atomiche;

import java.util.Set;

import _framework.Task;
import _gestioneeventi.Environment;
import _gestioneeventi.EsecuzioneEnvironment;
import _gestioneeventi.Listener;
import applicazione.TipoLinkContiene;
import applicazione.barca.Barca;
import applicazione.dispositivo.Dispositivo;
import applicazione.esplorazione.Esplorazione;
import applicazione.eventi.InvioImmersione;

public class AvviaEsplorazione implements Task {
	
	public Esplorazione esplorazione;

	private boolean eseguita = false;
	
	public AvviaEsplorazione(Esplorazione e) {
		this.esplorazione = e;
	}

	@Override
	public synchronized void esegui() {
		if(eseguita)
			return;
		eseguita = true;
		
		Set<Dispositivo> dispositivi = esplorazione.getAssociato();
		for(Dispositivo d : dispositivi) {
			EsecuzioneEnvironment.addListener((Listener) d);
			if(d.getClass() == Barca.class) {
				Barca curB = (Barca) d;
				for(TipoLinkContiene tlc : curB.getLinkContiene()) {
					Environment.aggiungiEvento(new InvioImmersione(null, 
							curB,
							tlc.getSottomarino(),
							((int)(Math.random() * 100) + 1)));  // profondita' tra 1 e 100);
				}
			}
		}
		System.out.println("Stato Environment: " + EsecuzioneEnvironment.getStato());
		EsecuzioneEnvironment.attivaListener();
		System.out.println("Stato Environment: " + EsecuzioneEnvironment.getStato());
	}
	
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
