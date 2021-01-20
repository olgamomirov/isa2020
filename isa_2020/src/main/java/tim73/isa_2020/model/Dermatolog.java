package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Dermatolog extends Korisnik{
	
	
	private static final long serialVersionUID = 1L;


	@ManyToMany(mappedBy = "dermatolozi")
	private Set<Apoteka> apoteke= new HashSet<Apoteka>();
	
	
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RadnoVreme> radnoVreme=new HashSet<RadnoVreme>();
	
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaDermatolog> oceneDermatologa = new HashSet<OcenaDermatolog>();


	public Dermatolog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dermatolog(String ime, String prezime, String email, String lozinka, String ulica, String grad,
			String drzava, String telefon, String status) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon, status);
		// TODO Auto-generated constructor stub
	}

	public Set<Apoteka> getApoteke() {
		return apoteke;
	}

	public void setApoteke(Set<Apoteka> apoteke) {
		this.apoteke = apoteke;
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

	public Set<OcenaDermatolog> getOceneDermatologa() {
		return oceneDermatologa;
	}

	public void setOceneDermatologa(Set<OcenaDermatolog> oceneDermatologa) {
		this.oceneDermatologa = oceneDermatologa;
	}

	
}
