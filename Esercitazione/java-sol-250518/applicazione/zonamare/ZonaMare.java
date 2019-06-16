package applicazione.zonamare;

public class ZonaMare {
	
	protected final double lat;
	protected final double lon;
	protected String descrizione;
	
	public ZonaMare(double lat, double lon, String descrizione) {
		this.lat = lat;
		this.lon = lon;
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
}
