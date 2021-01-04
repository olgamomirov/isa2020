package tim73.isa_2020.dto;

import tim73.isa_2020.model.Lek;

public class LekarDTO {
	
	private String imeIPrezime;
	private String specijalizacija;
	
	
	public LekarDTO(String imeIPrezime, String specijalizacija) {
		super();
		this.imeIPrezime = imeIPrezime;
		this.specijalizacija = specijalizacija;
	}
	
	
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public String getSpecijalizacija() {
		return specijalizacija;
	}
	
	


}
