package tim73.isa_2020.dto;


import org.joda.time.DateTime;
import org.joda.time.Interval;

import tim73.isa_2020.model.Pregled;

public class PregledDTO {

	private Long id;
	
	private DateTime start = new DateTime();
	
	private DateTime stop = new DateTime();
	
	private Interval interval  = new org.joda.time.Interval( start, stop );
	
    private String status = "default";
	
	private String dijagnoza;
	
	private String terapija;
	
	private String pacijentEmail;
	
	private String pacijentIme;
	
	private String pacijentPrezime;
	
	private String tipPregleda;
	
	private double cena;
	
	public PregledDTO (Pregled pregled, String email, String ime, String prezime) {
		this(pregled.getId(), pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(), email, ime, prezime);
	}
	public PregledDTO (Pregled pregled) {
		this(pregled.getId(), pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(),pregled.getTip().getTip(),pregled.getTip().getCena());
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}
    public PregledDTO(Long id, String interval, String status, String dijagnoza, String terapija, String tip, double cena) {
		
		this.id = id;
		this.interval = new Interval(interval);
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.tipPregleda = tip;
		this.cena = cena;
		
	}
	public PregledDTO(Long id, String interval, String status, String dijagnoza, String terapija, String email, String ime, String prezime) {
		
		this.id = id;
		this.interval = new Interval(interval);
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.pacijentEmail = email;
		this.pacijentIme = ime;
		this.pacijentPrezime = prezime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DateTime getStop() {
		return stop;
	}
	public void setStop(DateTime stop) {
		this.stop = stop;
	}
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public String getTerapija() {
		return terapija;
	}
	public void setTerapija(String terapija) {
		this.terapija = terapija;
	}
	public String getPacijentEmail() {
		return pacijentEmail;
	}
	public void setPacijentEmail(String pacijentEmail) {
		this.pacijentEmail = pacijentEmail;
	}
	public String getTipPregleda() {
		return tipPregleda;
	}
	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getPacijentIme() {
		return pacijentIme;
	}
	public void setPacijentIme(String pacijentIme) {
		this.pacijentIme = pacijentIme;
	}
	public String getPacijentPrezime() {
		return pacijentPrezime;
	}
	public void setPacijentPrezime(String pacijentPrezime) {
		this.pacijentPrezime = pacijentPrezime;
	}
}
