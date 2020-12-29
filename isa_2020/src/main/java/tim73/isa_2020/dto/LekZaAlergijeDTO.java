package tim73.isa_2020.dto;

import tim73.isa_2020.model.Lek;

public class LekZaAlergijeDTO {

	private String naziv;

	public LekZaAlergijeDTO(Lek lek) {
		this(lek.getSifrarnikLekova().getNaziv());
	}

	public LekZaAlergijeDTO(String naziv) {
		this.naziv = naziv;
	}

	public LekZaAlergijeDTO() {
		super();
		
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
}
