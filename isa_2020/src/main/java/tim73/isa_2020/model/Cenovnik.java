package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cenovnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String interval; //period vazenja jedne cene
	
	@ManyToOne
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "cenovnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CenovnikStavka> stavkeCenovnika = new HashSet<CenovnikStavka>();

	public Cenovnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cenovnik(String interval, Apoteka apoteka) {
		super();
		this.interval = interval;
		this.apoteka = apoteka;
	}

	public Cenovnik(String interval, Apoteka apoteka, Set<CenovnikStavka> stavkeCenovnika) {
		super();
		this.interval = interval;
		this.apoteka = apoteka;
		this.stavkeCenovnika = stavkeCenovnika;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public Set<CenovnikStavka> getStavkeCenovnika() {
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(Set<CenovnikStavka> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}

	public Long getId() {
		return id;
	}

}
