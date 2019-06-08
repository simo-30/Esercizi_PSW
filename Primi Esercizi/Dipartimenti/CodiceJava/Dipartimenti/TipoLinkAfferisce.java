public class TipoLinkAfferisce {
	private final Impiegato impiegato;
	private final Dipartimento dipartimento;
	private final Data dataAfferenza; //attributo
	
	public TipoLinkAfferisce(Impiegato i, Dipartimento dip, Data d) throws EccezioniPrecondizioni{
		if (i==null || dip==null || d==null) {
			throw new EccezioniPrecondizioni("Gli oggetti devono essere inizializzati");
		}
		impiegato=i;
		dipartimento=dip;
		dataAfferenza=d;
	}
	
	public Impiegato getImpiegato() {
		return impiegato;
	}
	
	public Dipartimento getDipartimento() {
		return dipartimento;
	}
	
	public Data getDataAfferenza() {
		return dataAfferenza;
	}
	
	public equals(Object o) {
		if (o!=null && getClass().equals(o.getClass())) {
			TipoLinkAfferisce a=(TipoLinkAfferisce)o;
			return a.getImpiegato()==impiegato && a.getDipartimento()==dipartimento;
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return impiegato.hashCode() + dipartimento.hashCode();
	}
}
