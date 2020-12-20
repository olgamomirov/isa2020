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
	
	private String pacijentIme;
	
	private String pacijentPrezime;
	
	public PregledDTO (Pregled pregled, String ime, String prezime) {
		this(pregled.getId(), pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(), ime, prezime);
	}
	public PregledDTO (Pregled pregled) {
		this(pregled.getId(), pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija());
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}
    public PregledDTO(Long id, Interval interval, String status, String dijagnoza, String terapija) {
		
		this.id = id;
		this.interval = interval;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		
	}
	public PregledDTO(Long id, Interval interval, String status, String dijagnoza, String terapija, String ime, String prezime) {
		
		this.id = id;
		this.interval = interval;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.pacijentIme = ime;
		this.pacijentPrezime = prezime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Interval getInterval() {
		return interval;
	}

	public String getStatus() {
		return status;
	}

	public String getDijagnoza() {
		return dijagnoza;
	}

	public String getTerapija() {
		return terapija;
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
