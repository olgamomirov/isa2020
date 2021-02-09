package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;

public class ApotekaDTO {
	
	private Long id;
	private String naziv;
	private String grad;
	private String ulica;
	private String drzava;
	private double ocena;
	
	
	//za mape
	private double lat;
	private double lng;
	
	
	private double cena;
	private double cenaSaPopustom;
	




	public ApotekaDTO() {
		super();
		
	}


	public ApotekaDTO (Apoteka apoteka) {
		this(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(), apoteka.getOcena());
	}

	
	public ApotekaDTO (Apoteka apoteka, double ocena) {
		this(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(), ocena);
	}
	
	
	

	
	public ApotekaDTO(String naziv, String grad, String drzava, String ulica) {
		super();
		this.naziv = naziv;
		this.grad = grad;
		this.drzava = drzava;
		this.ulica = ulica;
	}


	public ApotekaDTO(Long id, String naziv, String grad, double ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.ocena=ocena;
	}
	
	public ApotekaDTO(Long id, String naziv, String grad, double ocena,double lat, double lng) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.ocena=ocena;
		this.lat=lat;
		this.lng=lng;
	}

	
	public ApotekaDTO(Long id, String naziv, String grad, String ulica, String drzava, double ocena, double lat,
			double lng) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.ulica = ulica;
		this.drzava = drzava;
		this.ocena = ocena;
		this.lat = lat;
		this.lng = lng;
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


	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}


	public String getUlica() {
		return ulica;
	}


	public void setUlica(String ulica) {
		this.ulica = ulica;
	}


	public String getDrzava() {
		return drzava;
	}


	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}


	public void setOcena(double ocena) {
		this.ocena = ocena;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public double getCenaSaPopustom() {
		return cenaSaPopustom;
	}


	public void setCenaSaPopustom(double cenaSaPopustom) {
		this.cenaSaPopustom = cenaSaPopustom;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApotekaDTO other = (ApotekaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}


	

}
