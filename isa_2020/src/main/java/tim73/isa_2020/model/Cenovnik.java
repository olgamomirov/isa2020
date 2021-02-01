package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cenovnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private double cena = 00.00;
	
	private String interval; //period vazenja jedne cene
	
	@ManyToOne
	private Lek lek;

	public Cenovnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cenovnik(double cena, String interval, Lek lek) {
		super();
		this.cena = cena;
		this.interval = interval;
		this.lek = lek;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public Long getId() {
		return id;
	}
	
}
