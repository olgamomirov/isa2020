package tim73.isa_2020.dto;

import java.util.List;

import org.joda.time.DateTime;

import tim73.isa_2020.model.Narudzbenica;

public class NarudzbenicaDTO {

    private Long id;
	
    private String status; 
    
	private DateTime rokPonude;
	
	private String emailAdmin;
	
	private boolean mozeCRUD;
	
	private List<StavkaNarudzbeniceDTO> stavke;
	
	public NarudzbenicaDTO(Narudzbenica n, boolean crud) {
		this(n.getId(), n.getRokPonude(), n.getStatus(), n.getAdministratorApoteke().getEmail(), crud);
	}
	
	public NarudzbenicaDTO(Narudzbenica n, List<StavkaNarudzbeniceDTO> stavke) {
		this(n.getId(), n.getRokPonude(), n.getStatus(), n.getAdministratorApoteke().getEmail(), stavke);
	}

	public NarudzbenicaDTO(Long id, String rokPonude, String status, String email, boolean crud) {
		super();
		this.id = id;
		this.rokPonude = new DateTime(rokPonude);
		this.status = status;
		this.emailAdmin = email;
		this.mozeCRUD = crud;
	}
	

	public NarudzbenicaDTO(Long id, String status, String rokPonude, String emailAdmin,
			List<StavkaNarudzbeniceDTO> stavke) {
		super();
		this.id = id;
		this.status = status;
		this.rokPonude = new DateTime(rokPonude);
		this.emailAdmin = emailAdmin;
		this.stavke = stavke;
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

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public boolean isMozeCRUD() {
		return mozeCRUD;
	}

	public void setMozeCRUD(boolean mozeCRUD) {
		this.mozeCRUD = mozeCRUD;
	}

	public List<StavkaNarudzbeniceDTO> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaNarudzbeniceDTO> stavke) {
		this.stavke = stavke;
	}
	
}
