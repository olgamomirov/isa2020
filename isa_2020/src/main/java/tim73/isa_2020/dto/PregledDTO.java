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
	
	
	public PregledDTO (Pregled pregled, String email) {
		this(pregled.getId(), pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(), email);
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
	public PregledDTO(Long id, Interval interval, String status, String dijagnoza, String terapija, String email) {
		
		this.id = id;
		this.interval = interval;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.pacijentEmail = email;
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

}
