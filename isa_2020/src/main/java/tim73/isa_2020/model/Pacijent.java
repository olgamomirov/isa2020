package tim73.isa_2020.model;

import javax.persistence.Entity;

@Entity
public class Pacijent extends Korisnik{

	public Pacijent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pacijent(String ime, String prezime, String email, String lozinka, String ulica, String grad, String drzava,
			String telefon) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon);
		// TODO Auto-generated constructor stub
	}

}
