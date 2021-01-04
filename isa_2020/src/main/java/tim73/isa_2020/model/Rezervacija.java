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
	
	private DateTime datumPreuzimanja;
	
	@ManyToOne
	private Lek lek;
	
	@ManyToOne
	private Pacijent pacijent;

	public Rezervacija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rezervacija(DateTime datumPreuzimanja, Lek lek, Pacijent pacijent) {
		super();
		this.datumPreuzimanja = datumPreuzimanja;
		this.lek = lek;
		this.pacijent = pacijent;
	}

	public DateTime getDatumPreuzimanja() {
		return datumPreuzimanja;
	}

	public void setDatumPreuzimanja(DateTime datumPreuzimanja) {
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
	
	
}
