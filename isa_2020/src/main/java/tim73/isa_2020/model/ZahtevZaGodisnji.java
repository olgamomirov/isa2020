package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ZahtevZaGodisnji {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	
	private String interval;
	
	private String status;
	
	private String razlogOdbijanja;
	
	@ManyToOne
	private Dermatolog dermatolog;
	
	@ManyToOne
	private Farmaceut farmaceut;
	
	@ManyToOne
	private Apoteka apoteka;

	
	
	public ZahtevZaGodisnji() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ZahtevZaGodisnji(String interval, String status, String razlog, Dermatolog dermatolog, Farmaceut farmaceut, Apoteka apoteka) {
		super();
		this.interval = interval;
		this.status = status;
		this.razlogOdbijanja = razlog;
		this.dermatolog = dermatolog;
		this.farmaceut = farmaceut;
		this.apoteka = apoteka;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getInterval() {
		return interval;
	}



	public void setInterval(String interval) {
		this.interval = interval;
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



	public Apoteka getApoteka() {
		return apoteka;
	}



	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}



	public Long getId() {
		return id;
	}


	public String getRazlogOdbijanja() {
		return razlogOdbijanja;
	}


	public void setRazlogOdbijanja(String razlogOdbijanja) {
		this.razlogOdbijanja = razlogOdbijanja;
	}

}
