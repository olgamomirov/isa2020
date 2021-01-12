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
	
	


}
