package tim73.isa_2020.dto;

import tim73.isa_2020.model.CenovnikStavka;

public class CenovnikStavkaDTO {

	private Long id;
	
	private double cena;
	
	private String nazivLeka;

	public CenovnikStavkaDTO(CenovnikStavka stavka) {
		
		this(stavka.getId(), stavka.getCena(), stavka.getLek().getSifrarnikLekova().getNaziv());
	}
	
	public CenovnikStavkaDTO(Long id, double cena, String nazivLeka) {
		super();
		this.id = id;
		this.cena = cena;
		this.nazivLeka = nazivLeka;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}

	public Long getId() {
		return id;
	}	
	
}
