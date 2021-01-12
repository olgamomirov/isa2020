package tim73.isa_2020.dto;

public class RezervacijaPregledaKodFarmaceutaDTO {

	private String datum;
	private Long idApoteke;
	private Long idFarmaceuta;
	
	
	
	public RezervacijaPregledaKodFarmaceutaDTO() {
		super();
	}

	public RezervacijaPregledaKodFarmaceutaDTO(String datum, Long idApoteke, Long idFarmaceuta) {
		super();
		this.datum = datum;
		this.idApoteke = idApoteke;
		this.idFarmaceuta = idFarmaceuta;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Long getIdApoteke() {
		return idApoteke;
	}

	public void setIdApoteke(Long idApoteke) {
		this.idApoteke = idApoteke;
	}

	public Long getIdFarmaceuta() {
		return idFarmaceuta;
	}

	public void setIdFarmaceuta(Long idFarmaceuta) {
		this.idFarmaceuta = idFarmaceuta;
	}
	
	
}
