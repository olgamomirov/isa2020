package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OcenaFarmaceut {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private int vrednost;
	
	@ManyToOne
	private Pacijent pacijent;
	
	
	@ManyToOne
	private Farmaceut farmaceut;


	public OcenaFarmaceut() {
		super();
	}


	public OcenaFarmaceut(int vrednost) {
		super();
		this.vrednost = vrednost;
	}


	public int getVrednost() {
		return vrednost;
	}


	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}


	public Pacijent getPacijent() {
		return pacijent;
	}


	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}


	public Farmaceut getFarmaceut() {
		return farmaceut;
	}


	public void setFarmaceut(Farmaceut farmaceut) {
		this.farmaceut = farmaceut;
	}


	public Long getId() {
		return id;
	}
	
	

}
