package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pacijent extends Korisnik{
	
	
	private static final long serialVersionUID = 1L;
	
	private int penal = 0;

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "alergija_id", referencedColumnName = "id")
	private Alergije alergija;
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ERecept> eRecept;


	public Pacijent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pacijent(String ime, String prezime, String email, String lozinka, String ulica, String grad, String drzava,
			String telefon, String status, int penal) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon, status);
		// TODO Auto-generated constructor stub
		this.penal = penal;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Set<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(Set<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Alergije getAlergija() {
		return alergija;
	}

	public void setAlergija(Alergije alergija) {
		this.alergija = alergija;
	}

	public Set<ERecept> geteRecept() {
		return eRecept;
	}

	public void seteRecept(Set<ERecept> eRecept) {
		this.eRecept = eRecept;
	}

	public int getPenal() {
		return penal;
	}

	public void setPenal(int penal) {
		this.penal = penal;
	}

}
