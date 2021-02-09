package tim73.isa_2020.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity
public class Rezervacija {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id; //jedinstveni broj rezervacije preko koje ce se izdavati lek
	
	private String datumPreuzimanja;
	
	private String status;
	
	@ManyToOne
	private Lek lek;
	
	@ManyToOne
	private Pacijent pacijent;
	
	@ManyToOne
	private Apoteka apoteka;

	public Rezervacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rezervacija(String datumPreuzimanja, String status, Lek lek, Pacijent pacijent,Apoteka apoteka) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.status = status;
		this.lek = lek;
		this.pacijent = pacijent;
		this.apoteka=apoteka;
	}

	public String getDatumPreuzimanja() {
		return datumPreuzimanja;
	}

	public void setDatumPreuzimanja(String datumPreuzimanja) {
		this.datumPreuzimanja = datumPreuzimanja;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}
	
	
}
