package tim73.isa_2020.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

@Entity
public class RadnoVreme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;


	
	
	private String interval  ;
	
	
	@ManyToOne
	private Dermatolog dermatolog;
	
	@ManyToOne
	private Farmaceut farmaceut;
	
	@ManyToOne
	private Apoteka apoteka;


	public RadnoVreme() {
		super();
		
	}


	public RadnoVreme(String interval) {
		super();
		
		this.interval = interval;
	}


	


	public String getInterval() {
		return interval;
	}


	public void setInterval(String interval) {
		this.interval = interval;
	}


	public Long getId() {
		return id;
	}
	
	


	public Dermatolog getDermatolog() {
		return dermatolog;
	}


	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}


	public Apoteka getApoteka() {
		return apoteka;
	}


	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	

	public Farmaceut getFarmaceut() {
		return farmaceut;
	}


	public void setFarmaceut(Farmaceut farmaceut) {
		this.farmaceut = farmaceut;
	}


	

	
	

}