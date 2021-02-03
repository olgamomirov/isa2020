package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CenovnikStavka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private double cena = 00.00;
	
	@ManyToOne
	private Lek lek;
	
	@ManyToOne
	private Cenovnik cenovnik;

	public CenovnikStavka() {
		super();
		
	}

	public CenovnikStavka(double cena, Lek lek, Cenovnik cenovnik) {
		super();
		this.cena = cena;
		this.lek = lek;
		this.cenovnik = cenovnik;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Long getId() {
		return id;
	}
}
