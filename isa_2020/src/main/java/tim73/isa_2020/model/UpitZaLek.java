package tim73.isa_2020.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UpitZaLek {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	private Lek lek; //ako vise dermatologa posalje upit za isti lek
	
	private String status; //pregledan i nepregledan (od strane administratora)
	
	private String datumSlanjaUpita;

	public UpitZaLek() {
		super();
	}

	public UpitZaLek(Lek lek, String status, String datumSlanjaUpita) {
		super();
		this.lek = lek;
		this.status = status;
		this.datumSlanjaUpita = datumSlanjaUpita;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatumSlanjaUpita() {
		return datumSlanjaUpita;
	}

	public void setDatumSlanjaUpita(String datumSlanjaUpita) {
		this.datumSlanjaUpita = datumSlanjaUpita;
	}

	public Long getId() {
		return id;
	}
	
}
