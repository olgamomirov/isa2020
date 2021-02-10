package tim73.isa_2020.dto;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import tim73.isa_2020.model.Pregled;

public class PregledZaPacijentaDTO {
	
private Long id;
	
	private String start ;
	
	private String status;

	private String dijagnoza;

	private String terapija;

	private String lekar;
	
	private String apoteka;
	
	private double cena;
	
	private double cenaSaPopustom;
	
	private double trajanje; //u minutima
	
	public PregledZaPacijentaDTO (Pregled pregled, String lekar,String start, double cena, double trajanje) {
		this(pregled.getId(),  pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(),   pregled.getApoteka().getNaziv(),lekar, start, cena, trajanje);
	}
	
	public PregledZaPacijentaDTO (Pregled pregled, String lekar,String start, double cena, double cenaSaPopustom,double trajanje) {
		this(pregled.getId(),  pregled.getStatus(), pregled.getDijagnoza(), pregled.getTerapija(),   pregled.getApoteka().getNaziv(),lekar, start, cena, cenaSaPopustom, trajanje);
	}
	
	public PregledZaPacijentaDTO(Long id,  String status, String dijagnoza, String terapija,  String apoteka,String lekar,String start, double cena, double trajanje) {
		
		this.id = id;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.apoteka=apoteka;
		this.lekar=lekar;
		this.start=start;
		this.cena=cena;
		this.trajanje=trajanje;
		
	}
	
public PregledZaPacijentaDTO(Long id,  String status, String dijagnoza, String terapija,  String apoteka,String lekar,String start, double cena,double cenaSaPopustom, double trajanje) {
		
		this.id = id;
		this.status = status;
		this.dijagnoza = dijagnoza;
		this.terapija = terapija;
		this.apoteka=apoteka;
		this.lekar=lekar;
		this.start=start;
		this.cena=cena;
		this.trajanje=trajanje;
		this.cenaSaPopustom=cenaSaPopustom;
		
	}

	public Long getId() {
		return id;
	}

	public String getStart() {
		return start;
	}

	public String getStatus() {
		return status;
	}

	public String getDijagnoza() {
		return dijagnoza;
	}

	public String getTerapija() {
		return terapija;
	}

	public String getLekar() {
		return lekar;
	}

	public String getApoteka() {
		return apoteka;
	}

	public double getCena() {
		return cena;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public double getCenaSaPopustom() {
		return cenaSaPopustom;
	}
	
	

}
