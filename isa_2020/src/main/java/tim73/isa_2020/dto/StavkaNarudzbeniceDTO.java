package tim73.isa_2020.dto;

import tim73.isa_2020.model.StavkaNarudzbenice;

public class StavkaNarudzbeniceDTO {

    private Long id;
	
	private int kolicina;
	
	private String nazivLeka;
	
	public StavkaNarudzbeniceDTO(StavkaNarudzbenice s) {
		this(s.getId(), s.getKolicina(), s.getLek().getSifrarnikLekova().getNaziv());
	}

	public StavkaNarudzbeniceDTO(Long id, int kolicina, String nazivLeka) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.nazivLeka = nazivLeka;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
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
