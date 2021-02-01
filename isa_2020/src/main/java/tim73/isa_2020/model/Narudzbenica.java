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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Narudzbenica {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String rokPonude;
	
	@ManyToOne
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<StavkaNarudzbenice>();

	public Narudzbenica() {
		super();
		
	}

	public Narudzbenica(String rokPonude, Apoteka apoteka) {
		super();
		this.rokPonude = rokPonude;
		this.apoteka = apoteka;
	}

	public Narudzbenica(String rokPonude, Apoteka apoteka, Set<StavkaNarudzbenice> stavkeNarudzbenice) {
		super();
		this.rokPonude = rokPonude;
		this.apoteka = apoteka;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public String getRokPonude() {
		return rokPonude;
	}

	public void setRokPonude(String rokPonude) {
		this.rokPonude = rokPonude;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public Set<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(Set<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public Long getId() {
		return id;
	}

}
