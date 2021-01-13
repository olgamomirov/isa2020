package tim73.isa_2020.dto;

import java.sql.Date;

import org.joda.time.DateTime;

import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Rezervacija;

public class RezervacijaDTO {

	private Long id;
	
	private DateTime datumPreuzimanja;
	
	private String nazivLeka;
	
	private String nazivApoteke;
	
	private String status;
	
	
	public RezervacijaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RezervacijaDTO (Rezervacija rezervacija) {
		this(rezervacija.getId(), rezervacija.getDatumPreuzimanja(), rezervacija.getStatus(), rezervacija.getLek().getSifrarnikLekova().getNaziv(), rezervacija.getLek().getApoteka().getNaziv());
	}


	public RezervacijaDTO(Long id, String datumPreuzimanja, String status, String nazivLeka, String nazivApoteke) {
		
		this.id = id;
		
		this.datumPreuzimanja = new DateTime (datumPreuzimanja);
		
		this.status = status;
		
		this.nazivLeka = nazivLeka;
		
		this.nazivApoteke = nazivApoteke;
	}


	public Long getId() {
		return id;
	}


	public DateTime getDatumPreuzimanja() {
		return datumPreuzimanja;
	}


	public String getNazivLeka() {
		return nazivLeka;
	}


	public String getNazivApoteke() {
		return nazivApoteke;
	}


	public String getStatus() {
		return status;
	}

	
}
