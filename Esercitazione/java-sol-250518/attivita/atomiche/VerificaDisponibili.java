package attivita.atomiche;

import java.util.HashMap;
import java.util.Set;

import _framework.Task;
import applicazione.TipoLinkContiene;
import applicazione.TipoLinkInclude;
import applicazione.barca.Barca;
import applicazione.batiscafo.Batiscafo;
import applicazione.dispositivo.Dispositivo;
import applicazione.esplorazione.Esplorazione;
import applicazione.sottomarino.Sottomarino;

public class VerificaDisponibili implements Task{

	public static enum DaContare{
		SOTTOMARINO, BATISCAFO
	};
	
	private boolean eseguita = false;
	
	private Esplorazione esp;
	private HashMap<Barca, HashMap<DaContare, Integer>> result;
	
	public VerificaDisponibili(Esplorazione e) {
		this.esp = e;
		this.result = new HashMap<>();
	}
	
	@Override
	public synchronized void esegui() {
		if(eseguita)
			return;
		eseguita = true;
		
		for (Dispositivo disp : esp.getAssociato()) {
			if(disp.getClass() == Barca.class) {
				Barca curB = (Barca) disp;
				HashMap<DaContare, Integer> counting = new HashMap<>();
				int nSott = 0, nBat = 0;
				Set<TipoLinkContiene> sottomariniInCur = curB.getLinkContiene();
				for(TipoLinkContiene tlc : sottomariniInCur) {
					Sottomarino s = tlc.getSottomarino();
					if(s.getStato() == Sottomarino.Stato.A_BORDO)
						nSott++;
					Set<TipoLinkInclude> batiscafiInSottInCur = s.getLinkInclude();
					for(TipoLinkInclude tli : batiscafiInSottInCur) {
						Batiscafo b = tli.getBatiscafo();
						if(b.getStato() == Batiscafo.Stato.PRONTO)
							nBat++;
					}
				}
				counting.put(DaContare.SOTTOMARINO, nSott);
				counting.put(DaContare.BATISCAFO, nBat);
				result.put(curB, counting);
			}
		}
	}
	
	public synchronized boolean estEseguita() {
		return eseguita;
	}
	
	public synchronized HashMap<Barca, HashMap<DaContare, Integer>> getResult() {
		if(!eseguita)
			throw new RuntimeException("Impossibile ottenere il risultato, lanciare prima l'attivita'!");
		return result;
	}
}
