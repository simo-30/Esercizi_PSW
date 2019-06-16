package attivita.complesse;

import _framework.TaskExecutor;
import _gestioneeventi.EsecuzioneEnvironment;
import applicazione.esplorazione.Esplorazione;
import attivita.atomiche.InterrompiEsplorazione;
import attivita.atomiche.AvviaEsplorazione;
import attivita.io.SegnaliIO;

public class Esplora implements Runnable {
	
	private Esplorazione esplorazione;
	
	private boolean eseguita = false;

	public Esplora(Esplorazione e) {
		this.esplorazione = e;
	}

	@Override
	public synchronized void run() {
		if(eseguita)
			return;
		eseguita = true;
		
		AvviaEsplorazione ad = new AvviaEsplorazione(esplorazione);
		TaskExecutor.getInstance().perform(ad);
		
		SegnaliIO.attendiStop();
		
		TaskExecutor.getInstance().perform(new InterrompiEsplorazione(esplorazione));
		
		// Attende completamento azioni di risalita
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		EsecuzioneEnvironment.disattivaListener();
		System.out.println("Stato Environment: " + EsecuzioneEnvironment.getStato());
	}
	
	public synchronized boolean estEseguita() {
		return eseguita;
	}

}
