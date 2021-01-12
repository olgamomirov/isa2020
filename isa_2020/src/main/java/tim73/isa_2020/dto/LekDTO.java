package tim73.isa_2020.dto;

import java.util.Set;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.SifrarnikLekova;

public class LekDTO {

	private Long id;
	
	private Long sifra;
	
	private String naziv;
	
	private String vrstaLeka;
	
	private String oblikLeka;
	
	private String sastav;
	
	private String proizvodjac;
	
	private boolean recept; //true- treba recept, false- ne treba 
	
	private String dodatneNapomene;
	
	private Long idApoteka;
	
	public LekDTO (Lek lek) {
		this(lek.getId(), lek.getSifrarnikLekova().getNaziv(), lek.getSifrarnikLekova().getId(), lek.getApoteka().getId(), 
				lek.getSifrarnikLekova().getVrstaLeka(), lek.getSifrarnikLekova().getOblikLeka(), lek.getSifrarnikLekova().getSastav(), 
				lek.getSifrarnikLekova().getProizvodjac(), lek.getSifrarnikLekova().getDodatneNapomene(), lek.getSifrarnikLekova().isRecept()
				);
					
				
	}

	public LekDTO(Long id, String naziv, Long sifra, Long idApoteka, String vrstaLeka, String oblikLeka, String sastav, String proizvodjac,
			String dodatneNapomene, boolean recept) {

          this.id = id;
          
          this.naziv = naziv;
          
          this.sifra = sifra;
          
          this.idApoteka = idApoteka;
          
          this.vrstaLeka = vrstaLeka;
          
          this.oblikLeka = oblikLeka;
          
          this.sastav = sastav;
          
          this.proizvodjac = proizvodjac;
          
          this.dodatneNapomene = dodatneNapomene;
          
          this.recept = recept;
         
	}

	public Long getId() {
		return id;
	}

	public Long getSifra() {
		return sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public Long getIdApoteka() {
		return idApoteka;
	}

	public String getVrstaLeka() {
		return vrstaLeka;
	}

	public String getOblikLeka() {
		return oblikLeka;
	}

	public String getSastav() {
		return sastav;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public boolean isRecept() {
		return recept;
	}

	public String getDodatneNapomene() {
		return dodatneNapomene;
	}
	
}
