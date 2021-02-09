package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Zalba {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String tekstZalbe;
	
	private String odgovor;
	
	@ManyToOne
	private Korisnik pacijent;
	
	@ManyToOne
	private Dermatolog dermatolog;
	
	@ManyToOne
	private Farmaceut farmaceut;
	
	@ManyToOne
	private Apoteka apoteka;
	
	
	public Zalba() {
		super();
		
	}

	public Zalba(String tekstZalbe, String odgovor, Korisnik pacijent, Apoteka apoteka) {
		super();
		this.tekstZalbe = tekstZalbe;
		this.odgovor = odgovor;
		this.pacijent = pacijent;
		this.apoteka = apoteka;
	}


	public Zalba(String tekstZalbe, String odgovor, Korisnik pacijent, Farmaceut farmaceut) {
		super();
		this.tekstZalbe = tekstZalbe;
		this.odgovor = odgovor;
		this.pacijent = pacijent;
		this.farmaceut = farmaceut;
	}


	public Zalba(String tekstZalbe, String odgovor, Korisnik pacijent, Dermatolog dermatolog) {
		super();
		this.tekstZalbe = tekstZalbe;
		this.odgovor = odgovor;
		this.pacijent = pacijent;
		this.dermatolog = dermatolog;
	}


	public String getTekstZalbe() {
		return tekstZalbe;
	}

	public void setTekstZalbe(String tekstZalbe) {
		this.tekstZalbe = tekstZalbe;
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

	public Korisnik getPacijent() {
		return pacijent;
	}

	public void setPacijent(Korisnik pacijent) {
		this.pacijent = pacijent;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

}
