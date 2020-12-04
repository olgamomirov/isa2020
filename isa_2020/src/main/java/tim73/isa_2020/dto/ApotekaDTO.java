package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;

public class ApotekaDTO {
	
	private Long id;
	private String naziv;
	
	public ApotekaDTO (Apoteka apoteka) {
		this(apoteka.getId(), apoteka.getNaziv());
	}

	public ApotekaDTO(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}


	public String getNaziv() {
		return naziv;
	}


	
	

}
