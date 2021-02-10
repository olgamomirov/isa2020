package tim73.isa_2020.dto;

import java.util.Date;

import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.UpitZaLek;

public class UpitZaLekDTO {
	
    private Long id;
	
	private String nazivLeka;
	
	private String status; 
	
	private DateTime datumSlanjaUpita;

	
	public UpitZaLekDTO() {
		super();
		
	}
	public UpitZaLekDTO(UpitZaLek upit) {
		this(upit.getId(), upit.getLek().getSifrarnikLekova().getNaziv(), upit.getStatus(), upit.getDatumSlanjaUpita());
	}
	
	public UpitZaLekDTO(Long id, String nazivLeka, String status, String datumSlanjaUpita) {
		super();
		this.id = id;
		this.nazivLeka = nazivLeka;
		this.status = status;
		this.datumSlanjaUpita = new DateTime(datumSlanjaUpita);
	}


	public Long getId() {
		return id;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public String getStatus() {
		return status;
	}

	public DateTime getDatumSlanjaUpita() {
		return datumSlanjaUpita;
	}

}
