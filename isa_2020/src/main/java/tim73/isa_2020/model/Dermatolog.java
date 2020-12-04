package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Dermatolog extends Korisnik{
	
	@ManyToMany(mappedBy = "dermatolozi")
	private Set<Apoteka> apoteke= new HashSet<Apoteka>();

	public Dermatolog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dermatolog(String ime, String prezime, String email, String lozinka, String ulica, String grad,
			String drzava, String telefon) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon);
		// TODO Auto-generated constructor stub
	}

	public Set<Apoteka> getApoteke() {
		return apoteke;
	}

	public void setApoteke(Set<Apoteka> apoteke) {
		this.apoteke = apoteke;
	}

	
}
