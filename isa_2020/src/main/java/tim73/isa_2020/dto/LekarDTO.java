package tim73.isa_2020.dto;

import tim73.isa_2020.model.Lek;

public class LekarDTO {
	
	private Long id;
	private String imeIPrezime;
	private String specijalizacija;
	private int ocena=0;
	
	
	public LekarDTO(String imeIPrezime, String specijalizacija) {
		super();
		this.imeIPrezime = imeIPrezime;
		this.specijalizacija = specijalizacija;
		
	}
	
	public LekarDTO(Long id, String imeIPrezime, String specijalizacija, int ocena) {
		super();
		this.id=id;
		this.imeIPrezime = imeIPrezime;
		this.specijalizacija = specijalizacija;
		this.ocena=ocena;
		
	}
	
	
	public Long getId() {
		return id;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public int getOcena() {
		return ocena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imeIPrezime == null) ? 0 : imeIPrezime.hashCode());
		result = prime * result + ocena;
		result = prime * result + ((specijalizacija == null) ? 0 : specijalizacija.hashCode());
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
		LekarDTO other = (LekarDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imeIPrezime == null) {
			if (other.imeIPrezime != null)
				return false;
		} else if (!imeIPrezime.equals(other.imeIPrezime))
			return false;
		if (ocena != other.ocena)
			return false;
		if (specijalizacija == null) {
			if (other.specijalizacija != null)
				return false;
		} else if (!specijalizacija.equals(other.specijalizacija))
			return false;
		return true;
	}
	
	


}
