package tim73.isa_2020.dto;

public class AkcijaPromocijaDTO {
	private String akcijePromocije;
	
	private String odKadVazi;
	
	private String doKadVazi;

	
	
	public AkcijaPromocijaDTO() {
		super();
	}

	public AkcijaPromocijaDTO(String akcijePromocije, String odKadVazi, String doKadVazi) {
		super();
		this.akcijePromocije = akcijePromocije;
		this.odKadVazi = odKadVazi;
		this.doKadVazi = doKadVazi;
	}

	public String getAkcijePromocije() {
		return akcijePromocije;
	}

	public void setAkcijePromocije(String akcijePromocije) {
		this.akcijePromocije = akcijePromocije;
	}

	public String getOdKadVazi() {
		return odKadVazi;
	}

	public void setOdKadVazi(String odKadVazi) {
		this.odKadVazi = odKadVazi;
	}

	public String getDoKadVazi() {
		return doKadVazi;
	}

	public void setDoKadVazi(String doKadVazi) {
		this.doKadVazi = doKadVazi;
	}
	
	
}
