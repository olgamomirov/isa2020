package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LoyaltyProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private int pragPoena;
	
	private String kategorijaKorisnika;
	
	private double popust;
	
	@OneToMany(mappedBy = "loyaltyProgram")
	private Set<Pacijent> pacijenti= new HashSet<Pacijent>();

	public LoyaltyProgram() {
		super();
	}

	public LoyaltyProgram(int pragPoena, String kategorijaKorisnika, double popust) {
		super();
		this.pragPoena = pragPoena;
		this.kategorijaKorisnika = kategorijaKorisnika;
		this.popust = popust;
	}

	public int getPragPoena() {
		return pragPoena;
	}

	public void setPragPoena(int pragPoena) {
		this.pragPoena = pragPoena;
	}

	public String getKategorijaKorisnika() {
		return kategorijaKorisnika;
	}

	public void setKategorijaKorisnika(String kategorijaKorisnika) {
		this.kategorijaKorisnika = kategorijaKorisnika;
	}

	public double getPopust() {
		return popust;
	}

	public void setPopust(double popust) {
		this.popust = popust;
	}

	public Set<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(Set<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}

	public Long getId() {
		return id;
	}

	
}
