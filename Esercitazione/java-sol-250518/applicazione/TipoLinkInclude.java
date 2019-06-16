package applicazione;

import applicazione.batiscafo.Batiscafo;
import applicazione.sottomarino.Sottomarino;

public class TipoLinkInclude {
	
	private final Sottomarino sottomarino;
	private final Batiscafo batiscafo;
	
	public TipoLinkInclude(Sottomarino s, Batiscafo b) {
		if(b == null || s == null)
			throw new EccezionePrecondizioni("Sono state violate le precondizioni in " + this.getClass());
		else {
			sottomarino = s;
			batiscafo = b;
		}
	}

	public Batiscafo getBatiscafo() {
		return batiscafo;
	}
	
	public Sottomarino getSottomarino() {
		return sottomarino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batiscafo == null) ? 0 : batiscafo.hashCode());
		result = prime * result + ((sottomarino == null) ? 0 : sottomarino.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoLinkInclude other = (TipoLinkInclude) obj;
		if (batiscafo == null) {
			if (other.batiscafo != null)
				return false;
		} else if (!batiscafo.equals(other.batiscafo))
			return false;
		if (sottomarino == null) {
			if (other.sottomarino != null)
				return false;
		} else if (!sottomarino.equals(other.sottomarino))
			return false;
		return true;
	}
}
