package applicazione.eventi;

import _gestioneeventi.Evento;
import _gestioneeventi.Listener;

public class RientroEffettuato extends Evento {

	public RientroEffettuato(Listener m, Listener d) {
		super(m, d);
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
