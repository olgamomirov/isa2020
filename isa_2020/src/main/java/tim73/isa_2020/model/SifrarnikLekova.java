package tim73.isa_2020.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SifrarnikLekova {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id; //sifra
	
	private String naziv;
	
	private String vrstaLeka;
	
	private String oblikLeka;
	
	private String sastav;
	
	private String proizvodjac;
	
	private boolean recept; //true- treba recept, false- ne treba 
	
	private String dodatneNapomene;
	
	@ManyToMany
	@JoinTable(name ="zamene_lekova", joinColumns = @JoinColumn(name="lek_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="zamena_id", referencedColumnName = "id"))
	private Set<SifrarnikLekova> zamenskiLekovi;
	
	@OneToOne(mappedBy = "sifrarnikLekova")
	private Lek lek;

	@ManyToMany(mappedBy = "lekovi")
	private Set<Alergije> alergije= new HashSet<Alergije>();
	
	
	@ManyToMany(mappedBy = "sifrarnici")
	private Set<ERecept> eRecepti;

	@OneToMany(mappedBy = "sifrarnikLekova", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaSifrarnikLekova> oceneLekova;

	
	public SifrarnikLekova(String naziv, String vrstaLeka, String oblikLeka, String sastav, String proizvodjac,
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

	public SifrarnikLekova() {
		super();
		
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

	public Set<SifrarnikLekova> getZamenskiLekovi() {
		return zamenskiLekovi;
	}

	public void setZamenskiLekovi(Set<SifrarnikLekova> zamenskiLekovi) {
		this.zamenskiLekovi = zamenskiLekovi;
	}

	public Long getId() {
		return id;
	}

	

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public Set<Alergije> getAlergije() {
		return alergije;
	}

	public void setAlergije(Set<Alergije> alergije) {
		this.alergije = alergije;
	}

	public Set<ERecept> geteRecepti() {
		return eRecepti;
	}

	public void seteRecepti(Set<ERecept> eRecepti) {
		this.eRecepti = eRecepti;
	}

	public Set<OcenaSifrarnikLekova> getOceneLekova() {
		return oceneLekova;
	}

	public void setOceneLekova(Set<OcenaSifrarnikLekova> oceneLekova) {
		this.oceneLekova = oceneLekova;
	}
	
	
	

}
