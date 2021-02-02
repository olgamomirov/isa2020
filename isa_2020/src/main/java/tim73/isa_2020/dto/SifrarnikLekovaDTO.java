package tim73.isa_2020.dto;

import tim73.isa_2020.model.SifrarnikLekova;

public class SifrarnikLekovaDTO {

private Long id; //sifra
	
	private String naziv;
	
	private String vrstaLeka;
	
	private String oblikLeka;
	
	private String sastav;
	
	private String proizvodjac;
	
	private boolean recept; //true- treba recept, false- ne treba 
	
	private String dodatneNapomene;

	public SifrarnikLekovaDTO(SifrarnikLekova sifrarnik) {
		this(sifrarnik.getNaziv(), sifrarnik.getVrstaLeka(), sifrarnik.getOblikLeka(), sifrarnik.getSastav(), sifrarnik.getProizvodjac(), sifrarnik.isRecept(), sifrarnik.getDodatneNapomene());
		
	}
	
	
	public SifrarnikLekovaDTO(String naziv, String vrstaLeka, String oblikLeka, String sastav, String proizvodjac,
			boolean recept, String dodatneNapomene) {
		super();
		this.naziv = naziv;
		this.vrstaLeka = vrstaLeka;
		this.oblikLeka = oblikLeka;
		this.sastav = sastav;
		this.proizvodjac = proizvodjac;
		this.recept = recept;
		this.dodatneNapomene = dodatneNapomene;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getVrstaLeka() {
		return vrstaLeka;
	}

	public void setVrstaLeka(String vrstaLeka) {
		this.vrstaLeka = vrstaLeka;
	}

	public String getOblikLeka() {
		return oblikLeka;
	}

	public void setOblikLeka(String oblikLeka) {
		this.oblikLeka = oblikLeka;
	}

	public String getSastav() {
		return sastav;
	}

	public void setSastav(String sastav) {
		this.sastav = sastav;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public boolean isRecept() {
		return recept;
	}

	public void setRecept(boolean recept) {
		this.recept = recept;
	}

	public String getDodatneNapomene() {
		return dodatneNapomene;
	}

	public void setDodatneNapomene(String dodatneNapomene) {
		this.dodatneNapomene = dodatneNapomene;
	}

	public Long getId() {
		return id;
	}
	
}
