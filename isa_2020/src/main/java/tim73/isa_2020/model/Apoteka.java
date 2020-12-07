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

import tim73.isa_2020.model.Dermatolog;


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
	
	
	@ManyToMany
	@JoinTable(name ="zaposleni_dermatolozi", joinColumns = @JoinColumn(name="apoteka_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="dermatolog_id", referencedColumnName = "id"))
	private Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	
	
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorApoteke> administratorApoteke = new HashSet<AdministratorApoteke>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Lek> lekovi = new HashSet<Lek>();
	

	public Apoteka() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apoteka(String naziv, String ulica, String grad, String drzava, double ocena) {
		super();
		this.naziv = naziv;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.ocena = ocena;
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
	
	
}
