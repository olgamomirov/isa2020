package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;

public class ApotekaDTO {
	
	private Long id;
	private String naziv;
	private String grad;
	private double ocena;
	
	public ApotekaDTO (Apoteka apoteka) {
		this(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(), apoteka.getOcena());
	}

	public ApotekaDTO(Long id, String naziv, String grad, double ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.ocena=ocena;
	}

	public Long getId() {
		return id;
	}


	public String getNaziv() {
		return naziv;
	}

	public String getGrad() {
		return grad;
	}

	
	public double getOcena() {
		return ocena;
	}

	

	
	

}
