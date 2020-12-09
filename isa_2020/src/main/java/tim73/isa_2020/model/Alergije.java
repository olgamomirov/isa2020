package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Alergije {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@OneToOne(mappedBy = "alergija")
	private Pacijent pacijent;
	
	@ManyToMany
	@JoinTable(name ="alergije_na_lek", joinColumns = @JoinColumn(name="alergija_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="lek_id", referencedColumnName = "id"))
	private Set<Lek> lekovi = new HashSet<Lek>(); 

	public Alergije() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Long getId() {
		return id;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}

}
