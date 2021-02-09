package tim73.isa_2020.model;

import javax.persistence.Entity;

@Entity
public class AdministratorSistema extends Korisnik{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministratorSistema() {
		super();
		
	}

	public AdministratorSistema(String ime, String prezime, String email, String lozinka, String ulica, String grad,
			String drzava, String telefon, String status) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon, status);
		
	}

	
	
}
