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

	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Pregled> pregledi = new HashSet<Pregled>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "alergija_id", referencedColumnName = "id")
	private Alergije alergija;

	public Pacijent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pacijent(String ime, String prezime, String email, String lozinka, String ulica, String grad, String drzava,
			String telefon) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon);
		// TODO Auto-generated constructor stub
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

}
