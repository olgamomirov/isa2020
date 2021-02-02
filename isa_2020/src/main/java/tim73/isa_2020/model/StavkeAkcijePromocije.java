package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StavkeAkcijePromocije {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	
	
	
	@ManyToOne
	private Lek lek;
	
	@ManyToOne
	private AkcijaPromocija akcijaPromocija;

	public StavkeAkcijePromocije() {
		super();
	}
	
	

	public StavkeAkcijePromocije(Lek lek) {
		super();
		this.lek = lek;
	}



	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public AkcijaPromocija getAkcijaPromocija() {
		return akcijaPromocija;
	}

	public void setAkcijaPromocija(AkcijaPromocija akcijaPromocija) {
		this.akcijaPromocija = akcijaPromocija;
	}
	
	
}
