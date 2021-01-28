package tim73.isa_2020.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AdministratorApoteke extends Korisnik{
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Apoteka apoteka ;

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

}
