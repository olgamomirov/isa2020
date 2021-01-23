package tim73.isa_2020.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OcenaDermatolog {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private int vrednost;

	public OcenaDermatolog() {
		super();
	}
	

	public OcenaDermatolog(int vrednost) {
		super();
		this.vrednost = vrednost;
	}


	@ManyToOne
	private Pacijent pacijent;
	
	
	@ManyToOne
	private Dermatolog dermatolog;


	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Dermatolog getDermatolog() {
		return dermatolog;
	}

	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}


	public int getVrednost() {
		return vrednost;
	}


	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}


	public Long getId() {
		return id;
	}

	
}
