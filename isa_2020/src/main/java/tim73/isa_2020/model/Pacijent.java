package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Pacijent extends Korisnik{
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();

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
	
	

}
