package tim73.isa_2020.dto;

import org.joda.time.DateTime;

import tim73.isa_2020.model.Narudzbenica;

public class NarudzbenicaDTO {

    private Long id;
	
    private String status; 
    
	private DateTime rokPonude;
	
	public NarudzbenicaDTO(Narudzbenica n) {
		this(n.getId(), n.getRokPonude(), n.getStatus());
	}

	public NarudzbenicaDTO(Long id, String rokPonude, String status) {
		super();
		this.id = id;
		this.rokPonude = new DateTime(rokPonude);
		this.status = status;
	}

	public DateTime getRokPonude() {
		return rokPonude;
	}

	public void setRokPonude(DateTime rokPonude) {
		this.rokPonude = rokPonude;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
