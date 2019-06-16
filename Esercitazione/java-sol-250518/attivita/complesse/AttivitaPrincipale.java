package attivita.complesse;

import applicazione.esplorazione.Esplorazione;
import attivita.io.SegnaliIO;

public class AttivitaPrincipale implements Runnable{

	private Esplorazione e;
	
	private boolean eseguita = false;
	
	public AttivitaPrincipale(Esplorazione e) {
		this.e = e;
	}
	
	@Override
	public synchronized void run() {
		if(eseguita)
			return;
		eseguita = true;
		Esplora esplora = new Esplora(e);
		Thread t1 = new Thread(esplora);
		t1.start();
		
		Verifica verifica = new Verifica(e);
		Thread t2 = new Thread(verifica);
		t2.start();
		
		try {
			t1.join();
			t2.join();
			SegnaliIO.stampaFine();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean estEseguita() {
		return eseguita;
	}

}
