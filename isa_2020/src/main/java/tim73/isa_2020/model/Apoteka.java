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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Apoteka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String naziv;
	
	private String ulica;
	
	private String grad;
	
	private String drzava;
	
	@Column(nullable=true)
	private double ocena;
	
	//za mape
	private double lat;
	
	private double lng;
	
	
	@ManyToMany
	@JoinTable(name ="zaposleni_dermatolozi", joinColumns = @JoinColumn(name="apoteka_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="dermatolog_id", referencedColumnName = "id"))
	private Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	
	
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorApoteke> administratorApoteke = new HashSet<AdministratorApoteke>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Lek> lekovi = new HashSet<Lek>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RadnoVreme> radnoVreme = new HashSet<RadnoVreme>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();

	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaApoteka> oceneApoteke = new HashSet<OcenaApoteka>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AkcijaPromocija> akcijePromocije =new HashSet<AkcijaPromocija>();
  
    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<Narudzbenica>();

    @OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<Cenovnik>();

	
	@ManyToMany
	@JoinTable(name ="akcije_promocije_pretplate", joinColumns = @JoinColumn(name="apoteka_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="pacijent_id", referencedColumnName = "id"))
	private Set<Pacijent> pacijenti = new HashSet<Pacijent>();
	
	
	public Apoteka() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apoteka(String naziv, String ulica, String grad, String drzava, double ocena, double lat, double lng) {
		super();
		this.naziv = naziv;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.ocena = ocena;
		this.lat=lat;
		this.lng=lng;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	

	public Set<Dermatolog> getDermatolozi() {
		return dermatolozi;
	}

	public void setDermatolozi(Set<Dermatolog> dermatolozi) {
		this.dermatolozi = dermatolozi;
	}

	public Set<Farmaceut> getFarmaceuti() {
		return farmaceuti;
	}

	public void setFarmaceuti(Set<Farmaceut> farmaceuti) {
		this.farmaceuti = farmaceuti;
	}

	public Set<AdministratorApoteke> getAdministratorApoteke() {
		return administratorApoteke;
	}

	public void setAdministratorApoteke(Set<AdministratorApoteke> administratorApoteke) {
		this.administratorApoteke = administratorApoteke;
	}

	public Long getId() {
		return id;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
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

	public Set<OcenaApoteka> getOceneApoteke() {
		return oceneApoteke;
	}

	public void setOceneApoteke(Set<OcenaApoteka> oceneApoteke) {
		this.oceneApoteke = oceneApoteke;
	}


	public Set<AkcijaPromocija> getAkcijePromocije() {
		return akcijePromocije;
	}

	public void setAkcijePromocije(Set<AkcijaPromocija> akcijePromocije) {
		this.akcijePromocije = akcijePromocije;
	}

	public Set<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(Set<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}	

	public Set<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}

	public void setNarudzbenice(Set<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}

  public Set<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(Set<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drzava == null) ? 0 : drzava.hashCode());
		result = prime * result + ((grad == null) ? 0 : grad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((ulica == null) ? 0 : ulica.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apoteka other = (Apoteka) obj;
		if (drzava == null) {
			if (other.drzava != null)
				return false;
		} else if (!drzava.equals(other.drzava))
			return false;
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (ulica == null) {
			if (other.ulica != null)
				return false;
		} else if (!ulica.equals(other.ulica))
			return false;
		return true;
	}


	

	
}
