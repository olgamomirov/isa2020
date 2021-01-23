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
	
	private double ocena;
	
	public LekDTO (Lek lek) {
		this(lek.getId(), lek.getSifrarnikLekova().getNaziv(), lek.getSifrarnikLekova().getId(), lek.getApoteka().getId(), 
				lek.getSifrarnikLekova().getVrstaLeka(), lek.getSifrarnikLekova().getOblikLeka(), lek.getSifrarnikLekova().getSastav(), 
				lek.getSifrarnikLekova().getProizvodjac(), lek.getSifrarnikLekova().getDodatneNapomene(), lek.getSifrarnikLekova().isRecept()
				);
					
				
	}
	
	public LekDTO (Lek lek,double ocena) {
		this(lek.getId(), lek.getSifrarnikLekova().getNaziv(), lek.getSifrarnikLekova().getId(), lek.getApoteka().getId(), 
				lek.getSifrarnikLekova().getVrstaLeka(), lek.getSifrarnikLekova().getOblikLeka(), lek.getSifrarnikLekova().getSastav(), 
				lek.getSifrarnikLekova().getProizvodjac(), lek.getSifrarnikLekova().getDodatneNapomene(), lek.getSifrarnikLekova().isRecept(),ocena
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
	
	public LekDTO(Long id, String naziv, Long sifra, Long idApoteka, String vrstaLeka, String oblikLeka, String sastav, String proizvodjac,
			String dodatneNapomene, boolean recept, double ocena) {

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
          
          this.ocena=ocena;

                  
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

	public double getOcena() {
		return ocena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dodatneNapomene == null) ? 0 : dodatneNapomene.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idApoteka == null) ? 0 : idApoteka.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((oblikLeka == null) ? 0 : oblikLeka.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ocena);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((proizvodjac == null) ? 0 : proizvodjac.hashCode());
		result = prime * result + (recept ? 1231 : 1237);
		result = prime * result + ((sastav == null) ? 0 : sastav.hashCode());
		result = prime * result + ((sifra == null) ? 0 : sifra.hashCode());
		result = prime * result + ((vrstaLeka == null) ? 0 : vrstaLeka.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LekDTO other = (LekDTO) obj;
		if (dodatneNapomene == null) {
			if (other.dodatneNapomene != null)
				return false;
		} else if (!dodatneNapomene.equals(other.dodatneNapomene))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idApoteka == null) {
			if (other.idApoteka != null)
				return false;
		} else if (!idApoteka.equals(other.idApoteka))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (oblikLeka == null) {
			if (other.oblikLeka != null)
				return false;
		} else if (!oblikLeka.equals(other.oblikLeka))
			return false;
		if (Double.doubleToLongBits(ocena) != Double.doubleToLongBits(other.ocena))
			return false;
		if (proizvodjac == null) {
			if (other.proizvodjac != null)
				return false;
		} else if (!proizvodjac.equals(other.proizvodjac))
			return false;
		if (recept != other.recept)
			return false;
		if (sastav == null) {
			if (other.sastav != null)
				return false;
		} else if (!sastav.equals(other.sastav))
			return false;
		if (sifra == null) {
			if (other.sifra != null)
				return false;
		} else if (!sifra.equals(other.sifra))
			return false;
		if (vrstaLeka == null) {
			if (other.vrstaLeka != null)
				return false;
		} else if (!vrstaLeka.equals(other.vrstaLeka))
			return false;
		return true;
	}
	
	
}
