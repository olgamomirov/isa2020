package tim73.isa_2020.dto;

import tim73.isa_2020.model.Apoteka;

public class ApotekaDTO {
	
	private Long id;
	private String naziv;
	private String grad;
	private double ocena;
	//za mape
	private double lat;
	private double lng;
	
	public ApotekaDTO (Apoteka apoteka) {
		this(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(), apoteka.getOcena());
	}

	public ApotekaDTO (Apoteka apoteka, double ocena) {
		this(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(), ocena);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grad == null) ? 0 : grad.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ocena);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
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
		if (Double.doubleToLongBits(ocena) != Double.doubleToLongBits(other.ocena))
			return false;
		return true;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	

	
	

}
