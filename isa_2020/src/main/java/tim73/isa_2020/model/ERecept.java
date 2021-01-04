package tim73.isa_2020.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity
public class ERecept {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	private Pacijent pacijent;
	
	private DateTime datumIzdavanja;
	
	private String status;
	
	@ManyToMany
	@JoinTable(name ="eRecepti", joinColumns = @JoinColumn(name="eRecept_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="sifra_leka_id", referencedColumnName = "id"))
	private Set<SifrarnikLekova> sifrarnici;

	public ERecept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERecept(Pacijent pacijent, DateTime datumIzdavanja, Set<SifrarnikLekova> sifrarnici, String status) {
		super();
		this.pacijent = pacijent;
		this.datumIzdavanja = datumIzdavanja;
		this.sifrarnici = sifrarnici;
		this.status= status;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public DateTime getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(DateTime datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public Set<SifrarnikLekova> getSifrarnici() {
		return sifrarnici;
	}

	public void setSifrarnici(Set<SifrarnikLekova> sifrarnici) {
		this.sifrarnici = sifrarnici;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
