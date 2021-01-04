package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;

public class LekDTO {

	private Long id;
	
	private Long sifra;
	
	private String naziv;
	
	private Long idApoteka;
	
	public LekDTO (Lek lek) {
		this(lek.getId(), lek.getSifrarnikLekova().getNaziv(), lek.getSifrarnikLekova().getId(), lek.getApoteka().getId());
	}

	public LekDTO(Long id, String naziv, Long sifra, Long idApoteka) {

          this.id = id;
          
          this.naziv = naziv;
          
          this.sifra = sifra;
          
          this.idApoteka = idApoteka;
	}

	public Long getId() {
		return id;
	}

	public Long getSifra() {
		return sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public Long getIdApoteka() {
		return idApoteka;
	}
	
	
}
