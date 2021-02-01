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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Lek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	
	private int kolicina = 0;
	
	@ManyToOne
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "lek")
	private Set<StavkaNarudzbenice> stavkeNarudzbenice= new HashSet<StavkaNarudzbenice>();
	
	@OneToMany(mappedBy = "lek", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sifra_leka_id", referencedColumnName = "id")
	private SifrarnikLekova sifrarnikLekova;
	
	@OneToMany(mappedBy = "lek", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<Cenovnik>();
	

	@OneToMany(mappedBy = "lek", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UpitZaLek> upitiZaLek = new HashSet<UpitZaLek>();
	
	
	public Lek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lek(int kolicina, Apoteka apoteka) {
		super();
		this.kolicina = kolicina;
		this.apoteka = apoteka;
	}
	
	
	public Lek(int kolicina, Apoteka apoteka, SifrarnikLekova sifrarnikLekova) {
		super();
		this.kolicina = kolicina;
		this.apoteka = apoteka;
		this.sifrarnikLekova = sifrarnikLekova;
	}

	public Set<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(Set<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
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

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public SifrarnikLekova getSifrarnikLekova() {
		return sifrarnikLekova;
	}

	public void setSifrarnikLekova(SifrarnikLekova sifrarnikLekova) {
		this.sifrarnikLekova = sifrarnikLekova;
	}

	public Set<UpitZaLek> getUpitiZaLek() {
		return upitiZaLek;
	}

	public void setUpitiZaLek(Set<UpitZaLek> upitiZaLek) {
		this.upitiZaLek = upitiZaLek;
	}
	
}
