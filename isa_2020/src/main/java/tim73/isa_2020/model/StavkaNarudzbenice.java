package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StavkaNarudzbenice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	private Lek lek;
	
	private int kolicina = 0;
	
	@ManyToOne
	private Narudzbenica narudzbenica;

	public StavkaNarudzbenice() {
		super();
		
	}

	public StavkaNarudzbenice(Lek lek, int kolicina, Narudzbenica narudzbenica) {
		super();
		this.lek = lek;
		this.kolicina = kolicina;
		this.narudzbenica = narudzbenica;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public Long getId() {
		return id;
	}
	
}
