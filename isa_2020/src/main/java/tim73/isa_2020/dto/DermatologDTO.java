package tim73.isa_2020.dto;

import tim73.isa_2020.model.Korisnik;

public class DermatologDTO {
	
	private String email;
	private String ime;
	private String prezime;
	private String ulica;
	private String grad;
	private String drzava;
	private String telefon;
	
	public DermatologDTO() {
		super();
		
	}
	

	public DermatologDTO (Korisnik k) {
		this(k.getEmail(), k.getIme(), k.getPrezime(), k.getUlica(), k.getGrad(), k.getDrzava(), k.getTelefon());
	}


	public DermatologDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon) {
		
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.telefon = telefon;
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
	
}
