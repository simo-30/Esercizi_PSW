package _gestioneeventi;

import java.util.*;
import java.util.concurrent.*;

public final class Environment { // NB con final non si possono definire
									// sottoclassi
	private Environment() { // NB non si possono costruire oggetti Environment
	}

	private static ConcurrentHashMap<Listener, LinkedBlockingQueue<Evento>> codeEventiDeiListener = 
		new ConcurrentHashMap<Listener, LinkedBlockingQueue<Evento>>();

	public static void addListener(Listener lr, EsecuzioneEnvironment e) {
		if (e == null)
			return;
		codeEventiDeiListener.put(lr, new LinkedBlockingQueue<Evento>());
		// Nota Listener inserito ma non attivo
	}
	
	public static Set<Listener> getInsiemeListener() {
		return codeEventiDeiListener.keySet();
	}

	public static void aggiungiEvento(Evento e) {
		// unico meccanismo per aggiungere eventi
		if (e == null)
			return;
		Listener destinatario = e.getDestinatario();
		if (destinatario != null
				&& codeEventiDeiListener.containsKey(destinatario)) {
			// evento per un destinatario attivo
			try {
				codeEventiDeiListener.get(destinatario).put(e);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} else if (destinatario == null) { // evento in broadcasting
			Iterator<Listener> itn = codeEventiDeiListener.keySet().iterator();
			while (itn.hasNext()) {
				Listener lr = itn.next();
				try {
					codeEventiDeiListener.get(lr).put(e);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static Evento prossimoEvento(Listener lr)
			throws InterruptedException {
		// nota NON deve essere synchronized!!!
		return codeEventiDeiListener.get(lr).take();
	}

	public static void resetListener(EsecuzioneEnvironment e) {
		if (e == null)
			return;
		Iterator<Listener> itn = codeEventiDeiListener.keySet().iterator();
		while (itn.hasNext()) {
			Listener lr = itn.next();
			codeEventiDeiListener.put(lr, new LinkedBlockingQueue<Evento>());
		// Put sostituisce il vecchio valore associato alla chiave con il nuovo
		}
	}
		
}