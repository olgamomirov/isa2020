package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;

public class LekDTO {

	private Long id;
	
	private String sifra;
	
	private String naziv;
	
	private Long idApoteka;
	
	public LekDTO (Lek lek) {
		this(lek.getId(), lek.getNaziv(), lek.getSifra(), lek.getApoteka().getId());
	}

	public LekDTO(Long id, String naziv, String sifra, Long idApoteka) {

          this.id = id;
          
          this.naziv = naziv;
          
          this.sifra = sifra;
          
          this.idApoteka = idApoteka;
	}

	public Long getId() {
		return id;
	}

	public String getSifra() {
		return sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public Long getIdApoteka() {
		return idApoteka;
	}
	
	
}
