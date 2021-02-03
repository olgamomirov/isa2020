package tim73.isa_2020.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ponuda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private double ukupnaCena = 00.00;
	
	private String rokIsporuke;
	
	private String status;
	
	private boolean rokIstekao;
	
	@ManyToOne
	private Narudzbenica narudzbenica;
	
	@ManyToOne
	private Dobavljac dobavljac;

	public Ponuda() {
		super();
		
	}

	public Ponuda(double ukupnaCena, String rokIsporuke, String status, Narudzbenica narudzbenica, Dobavljac dobavljac) {
		super();
		this.ukupnaCena = ukupnaCena;
		this.rokIsporuke = rokIsporuke;
		this.status = status;
		this.narudzbenica = narudzbenica;
		this.dobavljac = dobavljac;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(String rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public Long getId() {
		return id;
	}

	public boolean isRokIstekao() {
		return rokIstekao;
	}

	public void setRokIstekao(boolean rokIstekao) {
		this.rokIstekao = rokIstekao;
	}

	public Dobavljac getDobavljac() {
		return dobavljac;
	}

	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}
	
}
