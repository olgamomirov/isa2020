package tim73.isa_2020.dto;

import java.sql.Date;

import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Rezervacija;

public class RezervacijaDTO {

	private Long id;
	
	private Date datumPreuzimanja;
	
	private String nazivLeka;
	
	private String nazivApoteke;
	
	
	public RezervacijaDTO (Rezervacija rezervacija) {
		this(rezervacija.getId(), rezervacija.getDatumPreuzimanja(), rezervacija.getLek().getSifrarnikLekova().getNaziv(), rezervacija.getLek().getApoteka().getNaziv());
	}


	public RezervacijaDTO(Long id, Date datumPreuzimanja, String nazivLeka, String nazivApoteke) {
		
		this.id = id;
		
		this.datumPreuzimanja = datumPreuzimanja;
		
		this.nazivLeka = nazivLeka;
		
		this.nazivApoteke = nazivApoteke;
	}


	public Long getId() {
		return id;
	}


	public Date getDatumPreuzimanja() {
		return datumPreuzimanja;
	}


	public String getNazivLeka() {
		return nazivLeka;
	}


	public String getNazivApoteke() {
		return nazivApoteke;
	}

	
}
