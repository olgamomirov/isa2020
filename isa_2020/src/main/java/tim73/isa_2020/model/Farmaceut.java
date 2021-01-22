package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Farmaceut extends Korisnik{
	
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Apoteka apoteka ;
	
	@OneToMany(mappedBy = "farmaceut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RadnoVreme> radnoVreme= new HashSet<>();

	@OneToMany(mappedBy = "farmaceut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	
	@OneToMany(mappedBy = "farmaceut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaFarmaceut> oceneFarmaceuta = new HashSet<OcenaFarmaceut>();
	
	
	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public Set<RadnoVreme> getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(Set<RadnoVreme> radnoVreme) {
		this.radnoVreme = radnoVreme;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Set<OcenaFarmaceut> getOceneFarmaceuta() {
		return oceneFarmaceuta;
	}

	public void setOceneFarmaceuta(Set<OcenaFarmaceut> oceneFarmaceuta) {
		this.oceneFarmaceuta = oceneFarmaceuta;
	}
	

}
