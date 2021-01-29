package tim73.isa_2020.dto;

import org.joda.time.Interval;

import tim73.isa_2020.model.Cenovnik;

public class CenovnikDTO {

	private Long id;
	
	private double cena;
	
	private Interval interval;

	
	public CenovnikDTO(Cenovnik cenovnik) {
		this(cenovnik.getCena(), cenovnik.getInterval());
	}
	
	public CenovnikDTO(double cena, String interval) {
		super();
		this.cena = cena;
		this.interval = new Interval(interval);
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public Long getId() {
		return id;
	}
	
}
