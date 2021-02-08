package tim73.isa_2020.dto;

public class AdminApotekaDTO {
	
	private String email;
	private String ime;
	private String prezime;
	private String ulica;
	private String grad;
	private String drzava;
	private String telefon;
	private String status;
	private Long apotekaId;
	
	
	
	public AdminApotekaDTO() {
		super();
		
	}
	
	
	public AdminApotekaDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon, String status, Long apotekaId) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.telefon = telefon;
		this.status = status;
		this.apotekaId = apotekaId;
	}


	public String getEmail() {
		return email;
	}
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public String getUlica() {
		return ulica;
	}
	public String getGrad() {
		return grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public String getTelefon() {
		return telefon;
	}
	public String getStatus() {
		return status;
	}
	public Long getApotekaId() {
		return apotekaId;
	}
	
}
