package applicazione;

import applicazione.barca.Barca;
import applicazione.sottomarino.Sottomarino;

public class TipoLinkContiene {
	
	private final Barca barca;
	private final Sottomarino sottomarino;
	
	public TipoLinkContiene(Barca b, Sottomarino s) {
		if(b == null || s == null)
			throw new EccezionePrecondizioni("Sono state violate le precondizioni in " + this.getClass());
		else {
			barca = b;
			sottomarino = s;
		}
	}

	public Barca getBarca() {
		return barca;
	}
	
	public Sottomarino getSottomarino() {
		return sottomarino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barca == null) ? 0 : barca.hashCode());
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
		TipoLinkContiene other = (TipoLinkContiene) obj;
		if (barca == null) {
			if (other.barca != null)
				return false;
		} else if (!barca.equals(other.barca))
			return false;
		if (sottomarino == null) {
			if (other.sottomarino != null)
				return false;
		} else if (!sottomarino.equals(other.sottomarino))
			return false;
		return true;
	}
}
