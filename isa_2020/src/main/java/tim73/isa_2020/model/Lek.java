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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lek {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	
	private String sifra;
	
	private String naziv;
	
	private int kolicina = 0;
	
	@ManyToOne
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "lek", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	@ManyToMany(mappedBy = "lekovi")
	private Set<Alergije> alergije= new HashSet<Alergije>();
	

	public Lek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lek(String sifra, String naziv, int kolicina, Apoteka apoteka) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.apoteka = apoteka;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public Set<Alergije> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<Alergije> alergije) {
		this.alergije = alergije;
	}
	
}
