package tim73.isa_2020.dto;

import java.util.List;

public class AkcijaPromocijaDTO {
	private String akcijePromocije;
	
	private String odKadVazi;
	 
	private String doKadVazi;
	
	private double procenat;
	
	private List<String> lekoviNaAkciji;

	
	
	public AkcijaPromocijaDTO() {
		super();
	}

	

	public AkcijaPromocijaDTO(String akcijePromocije, String odKadVazi, String doKadVazi, double procenat,
			List<String> lekoviNaAkciji) {
		super();
		this.akcijePromocije = akcijePromocije;
		this.odKadVazi = odKadVazi;
		this.doKadVazi = doKadVazi;
		this.procenat = procenat;
		this.lekoviNaAkciji = lekoviNaAkciji;
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



	public double getProcenat() {
		return procenat;
	}



	public void setProcenat(double procenat) {
		this.procenat = procenat;
	}



	public List<String> getLekoviNaAkciji() {
		return lekoviNaAkciji;
	}



	public void setLekoviNaAkciji(List<String> lekoviNaAkciji) {
		this.lekoviNaAkciji = lekoviNaAkciji;
	}
	 
	
}
