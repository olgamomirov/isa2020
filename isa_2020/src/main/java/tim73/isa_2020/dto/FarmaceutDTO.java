package tim73.isa_2020.dto;

import java.util.List;

import tim73.isa_2020.model.Korisnik;

public class FarmaceutDTO {

	private String email;
	private String ime;
	private String prezime;
	private String ulica;
	private String grad;
	private String drzava;
	private String telefon;
	private String status;
	private List<String> radnoVreme;
	
	public FarmaceutDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FarmaceutDTO (Korisnik k) {
		this(k.getEmail(), k.getIme(), k.getPrezime(), k.getUlica(), k.getGrad(), k.getDrzava(), k.getTelefon(), k.getStatus());
	}
	
	public FarmaceutDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon, String status) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.telefon = telefon;
		this.status = status;
	}
	
	public FarmaceutDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon, String status, List<String> radnoVreme) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.telefon = telefon;
		this.status = status;
		this.radnoVreme=radnoVreme;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRadnoVreme() {
		return radnoVreme;
	}

	public void setRadnoVreme(List<String> radnoVreme) {
		this.radnoVreme = radnoVreme;
	}

}
