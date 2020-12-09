package tim73.isa_2020.dto;

import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Pregled;

public class PregledDTO {

	
	private Interval interval;
	
    private String status = "default";
	
	private String dijagnoza;
	
	private String terapija;
	
	public PregledDTO (Pregled pregled) {
		this(pregled.getInterval(), pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija());
	}

	public PregledDTO(Interval interval, String status, String dijagnoza, String terapija) {
		
		this.interval = interval;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
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
	
}
