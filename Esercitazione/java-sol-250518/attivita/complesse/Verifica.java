package attivita.complesse;

import java.util.HashMap;

import _framework.TaskExecutor;
import applicazione.barca.Barca;
import applicazione.esplorazione.Esplorazione;
import attivita.atomiche.VerificaDisponibili;
import attivita.atomiche.VerificaDisponibili.DaContare;
import attivita.io.SegnaliIO;

public class Verifica implements Runnable {
	
	public Esplorazione esplorazione;

	private boolean eseguita = false;
	
	public Verifica(Esplorazione e) {
		this.esplorazione = e;
	}

	@Override
	public synchronized void run() {
		if(eseguita)
			return;
		eseguita = true;
		
		VerificaDisponibili vd = new VerificaDisponibili(esplorazione);
		TaskExecutor.getInstance().perform(vd);
		HashMap<Barca, HashMap<DaContare, Integer>> count = vd.getResult();
		
		SegnaliIO.stampaReport(count);
	}
	
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}