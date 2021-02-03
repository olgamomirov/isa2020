package tim73.isa_2020.dto;

import java.util.List;

import org.joda.time.Interval;

import tim73.isa_2020.model.Cenovnik;

public class CenovnikDTO {

	private Long id;
	
	
	private Interval interval;
	
	private List<CenovnikStavkaDTO> stavke;
	
	public CenovnikDTO(Cenovnik cenovnik) {
		this(cenovnik.getId(), cenovnik.getInterval());
	}
	
	public CenovnikDTO(Cenovnik cenovnik, List<CenovnikStavkaDTO> stavke) {
		this( cenovnik.getId(), cenovnik.getInterval(), stavke);
	}
	
	public CenovnikDTO( Long id, String interval) {
		super();
		this.id = id;
		this.interval = new Interval(interval);
	}

	public CenovnikDTO(Long id, String interval, List<CenovnikStavkaDTO> stavke) {
		super();
		this.id = id;
		this.interval = new Interval(interval);
		this.stavke = stavke;
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

	public List<CenovnikStavkaDTO> getStavke() {
		return stavke;
	}

	public void setStavke(List<CenovnikStavkaDTO> stavke) {
		this.stavke = stavke;
	}
	
}
