package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AkcijaPromocija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String akcijaPromocija;
	
	private String vremeVazenja;
	
	private double procenatAkcije;

	
	@ManyToOne
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "akcijaPromocija",  cascade = CascadeType.ALL)
	private Set<StavkeAkcijePromocije> stavke =new HashSet<StavkeAkcijePromocije>();

	public AkcijaPromocija() {
		super();
	}

	public AkcijaPromocija(String akcijaPromocija,String vremeVazenja, Apoteka apoteka, double procenatAkcije) {
		super();
		this.akcijaPromocija = akcijaPromocija;
		this.vremeVazenja=vremeVazenja;
		this.apoteka = apoteka;
		this.procenatAkcije = procenatAkcije;
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

	public Set<StavkeAkcijePromocije> getStavke() {
		return stavke;
	}

	public void setStavke(Set<StavkeAkcijePromocije> stavke) {
		this.stavke = stavke;
	}

	public double getProcenatAkcije() {
		return procenatAkcije;
	}

	public void setProcenatAkcije(double procenatAkcije) {
		this.procenatAkcije = procenatAkcije;
	}
	
	
}
