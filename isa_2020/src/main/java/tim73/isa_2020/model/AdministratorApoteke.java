package tim73.isa_2020.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AdministratorApoteke extends Korisnik{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Apoteka apoteka ;
	
	
	

	public AdministratorApoteke() {
		super();
	}

	public AdministratorApoteke(String ime, String prezime, String email, String lozinka, String ulica, String grad,
			String drzava, String telefon, String status) {
		super(ime, prezime, email, lozinka, ulica, grad, drzava, telefon, status);
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

}
