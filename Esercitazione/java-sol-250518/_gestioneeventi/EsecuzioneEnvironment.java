package _gestioneeventi;

import java.util.*;

/*
 * Serve ad aggiungere i singoli listener, ed ad attivarli e disattivali (tutti insieme)
 * Nota: implementa il seguente diagramma degli stati e delle transizioni :
 * 
 *    addListener
 *        __
 *       |  |   --attivaListener-> 
 *  --> Attesa                      Esecuzione 
 *            <--disattivaListener- 
 */

public final class EsecuzioneEnvironment { //NB con final non si possono definire sottoclassi
	private EsecuzioneEnvironment() {
	}

	public static enum Stato {
		Attesa, Esecuzione
	};

	private static Stato statocorrente = Stato.Attesa;


	private static HashMap<Listener, Thread> listenerAttivi = null;

	public static synchronized void addListener(Listener lr) {
		if (statocorrente == Stato.Attesa) {
			Environment.addListener(lr, new EsecuzioneEnvironment());
			//NB: Listener inserito ma non attivo
		}
	}

	public static synchronized void attivaListener() {
		if (statocorrente == Stato.Attesa) {
			statocorrente = Stato.Esecuzione;
			System.out.println("Ora attiviamo i listener");
			listenerAttivi = new HashMap<Listener, Thread>();
			Iterator<Listener> it = Environment.getInsiemeListener().iterator();
			while (it.hasNext()) {
				Listener listener = it.next();
				listenerAttivi.put(listener, new Thread(new EsecuzioneListener(listener)));
			}
			Iterator<Listener> i = listenerAttivi.keySet().iterator();
			while (i.hasNext()) {
				Listener l = i.next();
				listenerAttivi.get(l).start();
			}
		}
	}

	public static synchronized void disattivaListener() {
		if (statocorrente == Stato.Esecuzione) {
			statocorrente = Stato.Attesa;
			System.out.println("Ora fermiano i listener");
			Environment.aggiungiEvento(new Stop(null, null));
			// NB: a questo punto i listener non sono ancora fermi
			// ma l'evento Stop e' stato inserito nella coda di ciascuno di loro
			// e questo evento quando processato li disattivera'
			Iterator<Listener> it = listenerAttivi.keySet().iterator();
			while (it.hasNext()) {
				Listener listener = it.next();
				try {
					Thread thread = listenerAttivi.get(listener);
					thread.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			Environment.resetListener(new EsecuzioneEnvironment());
		}
	}

	public static synchronized Stato getStato() {
		return statocorrente;
	}
}
