package tim73.isa_2020.dto;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;

public class PacijentPodaciDTO {
	
	private String email;
	private String ime;
	private String prezime;
	private String ulica;
	private String grad;
	private String drzava;
	private String telefon;
	private int penali;
	
	public PacijentPodaciDTO (Korisnik korisnik) {
		this(korisnik.getEmail(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getUlica(), korisnik.getGrad(), korisnik.getDrzava(), korisnik.getTelefon());
	}
	
	public PacijentPodaciDTO (Korisnik korisnik, int penali) {
		this(korisnik.getEmail(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getUlica(), korisnik.getGrad(), korisnik.getDrzava(), korisnik.getTelefon(), penali);
	}
	

	public PacijentPodaciDTO() {
		super();
	}


	public PacijentPodaciDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon, int penali) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.drzava = drzava;
		this.telefon = telefon;
		this.penali=penali;
	}

	public PacijentPodaciDTO(String email, String ime, String prezime, String ulica, String grad, String drzava,
			String telefon) {
		super();
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getPenali() {
		return penali;
	}
	
	
	

}
