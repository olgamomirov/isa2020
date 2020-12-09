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

	@Transient
	private DateTimeZone timeZone = DateTimeZone.forID( "Europe/Paris" );


	private DateTime start = new DateTime( );
	
	private DateTime stop = new DateTime(  );
	
	private Interval interval  = new org.joda.time.Interval( start, stop );
	
	
	@ManyToOne
	private Dermatolog dermatolog;
	
	@ManyToOne
	private Farmaceut farmaceut;
	
	@ManyToOne
	private Apoteka apoteka;


	public RadnoVreme() {
		super();
		
	}


	public RadnoVreme(DateTime start, DateTime stop, Interval interval) {
		super();
		this.start = start;
		this.stop = stop;
		this.interval = interval;
	}


	public DateTime getStart() {
		return start;
	}


	public void setStart(DateTime start) {
		this.start = start;
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


	@Override
	public String toString() {
		return "RadnoVreme [start=" + start.toString("dd-MM-yyyy") + ", stop=" + stop + ", interval=" + interval + "]";
	}


	
	

}