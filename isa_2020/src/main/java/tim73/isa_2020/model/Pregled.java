package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

@Entity
public class Pregled {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Transient
	private DateTime start = new DateTime();
	
	@Transient
	private DateTime stop = new DateTime();
	
	
	private String interval ;
	
	private String status = "default";
	
	private String dijagnoza;
	
	private String terapija;
	
	@ManyToOne 
	private Apoteka apoteka;
	
	@ManyToOne
	private Pacijent pacijent;
	
	@ManyToOne
	private Dermatolog dermatolog;
	
	@ManyToOne
	private Farmaceut farmaceut;
	
	@ManyToOne
	private TipPregleda tip;

	public Pregled() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pregled(DateTime start, DateTime stop, String interval, String status, String dijagnoza, String terapija) {
		super();
		this.start = start;
		this.stop = stop;
		this.interval = interval;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
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

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
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

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Dermatolog getDermatolog() {
		return dermatolog;
	}

	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}

	public Farmaceut getFarmaceut() {
		return farmaceut;
	}

	public void setFarmaceut(Farmaceut farmaceut) {
		this.farmaceut = farmaceut;
	}

	public Long getId() {
		return id;
	}

	public TipPregleda getTip() {
		return tip;
	}

	public void setTip(TipPregleda tip) {
		this.tip = tip;
	}
	
}
