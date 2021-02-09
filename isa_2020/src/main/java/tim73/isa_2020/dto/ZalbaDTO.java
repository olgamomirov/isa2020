package tim73.isa_2020.dto;

import tim73.isa_2020.model.Zalba;

public class ZalbaDTO {

    private Long id;
	
	private String tekstZalbe;
	
	private String status;
	
	private String odgovor;
	
	private String dermatologIme;
	
	private String farmaceutIme;
	
	private String apotekaNaziv;
	
	private String imePacijenta;
	
	private String prezimePacijenta;
	
	public ZalbaDTO() {
		super();
		
	}
	
	public ZalbaDTO(Zalba z) {
		this(z.getId(), z.getTekstZalbe(), z.getStatus(), z.getOdgovor(), z.getPacijent().getIme(), z.getPacijent().getPrezime(), z.getDermatolog().getIme(), z.getFarmaceut().getIme(), z.getApoteka().getNaziv());
	}

	public ZalbaDTO(Long id, String tekstZalbe, String status, String odgovor, String ime, String prezime, String dermatologId, String farmaceutId, String apotekaId) {
		super();
		this.id = id;
		this.tekstZalbe = tekstZalbe;
		this.status = status;
		this.odgovor = odgovor;
		this.imePacijenta = ime;
		this.prezimePacijenta = prezime;
		this.dermatologIme = dermatologId;
		this.farmaceutIme = farmaceutId;
		this.apotekaNaziv = apotekaId;
	}


	public Long getId() {
		return id;
	}

	public String getTekstZalbe() {
		return tekstZalbe;
	}

	public String getDermatologIme() {
		return dermatologIme;
	}

	public String getFarmaceutIme() {
		return farmaceutIme;
	}

	public String getApotekaNaziv() {
		return apotekaNaziv;
	}

	public String getImePacijenta() {
		return imePacijenta;
	}

	public String getPrezimePacijenta() {
		return prezimePacijenta;
	}

	public String getStatus() {
		return status;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}
	
}
