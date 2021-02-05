package tim73.isa_2020.dto;

public class LozinkaDTO {

	private String stara;
	private String nova;
	
	

	public LozinkaDTO() {
		super();
	}

	
	public LozinkaDTO(String stara, String nova) {
		super();
		this.stara = stara;
		this.nova = nova;
	}


	public String getStara() {
		return stara;
	}


	public void setStara(String stara) {
		this.stara = stara;
	}


	public String getNova() {
		return nova;
	}


	public void setNova(String nova) {
		this.nova = nova;
	}


	
	
	
}
