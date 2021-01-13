package tim73.isa_2020.dto;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;

public class RadnoVremeDTO {
	
	
	
	
	private Interval interval;  
	
	public RadnoVremeDTO (RadnoVreme rv) {
		this(rv.getInterval());
	}

	public RadnoVremeDTO(String interval2) {
		this.interval = new Interval(interval2);
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
	

}
