package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AkcijaPromocija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String akcijaPromocija;
	
	private String vremeVazenja;

	
	@ManyToOne
	private Apoteka apoteka;

	public AkcijaPromocija() {
		super();
	}

	public AkcijaPromocija(String akcijaPromocija,String vremeVazenja, Apoteka apoteka) {
		super();
		this.akcijaPromocija = akcijaPromocija;
		this.vremeVazenja=vremeVazenja;
		this.apoteka = apoteka;
	}
	
	public AkcijaPromocija(String akcijaPromocija, String vremeVazenja) {
		super();
		this.akcijaPromocija = akcijaPromocija;
		this.vremeVazenja=vremeVazenja;
	}

	public String getAkcijaPromocija() {
		return akcijaPromocija;
	}

	public void setAkcijaPromocija(String akcijaPromocija) {
		this.akcijaPromocija = akcijaPromocija;
	}

	
	
	public String getVremeVazenja() {
		return vremeVazenja;
	}

	public void setVremeVazenja(String vremeVazenja) {
		this.vremeVazenja = vremeVazenja;
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
	
	
}
