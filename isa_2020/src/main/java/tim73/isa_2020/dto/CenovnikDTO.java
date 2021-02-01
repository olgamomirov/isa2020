package tim73.isa_2020.dto;

import org.joda.time.Interval;

import tim73.isa_2020.model.Cenovnik;

public class CenovnikDTO {

	private Long id;
	
	
	private Interval interval;

	
	public CenovnikDTO(Cenovnik cenovnik) {
		this( cenovnik.getInterval());
	}
	
	public CenovnikDTO( String interval) {
		super();
		
		this.interval = new Interval(interval);
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
