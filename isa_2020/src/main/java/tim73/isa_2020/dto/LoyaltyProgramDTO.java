package tim73.isa_2020.dto;

public class LoyaltyProgramDTO {

	private Long id;
	private String kategorijaKorisnika;
	private double popust;
	private int pragPoena;
	
	
	
	public LoyaltyProgramDTO() {
		super();
	}



	public LoyaltyProgramDTO(Long id,String kategorijaKorisnika, double popust, int pragPoena) {
		super();
		this.id=id;
		this.kategorijaKorisnika = kategorijaKorisnika;
		this.popust = popust;
		this.pragPoena = pragPoena;
	}



	public String getKategorijaKorisnika() {
		return kategorijaKorisnika;
	}



	public void setKategorijaKorisnika(String kategorijaKorisnika) {
		this.kategorijaKorisnika = kategorijaKorisnika;
	}



	public double getPopust() {
		return popust;
	}



	public void setPopust(double popust) {
		this.popust = popust;
	}



	public int getPragPoena() {
		return pragPoena;
	}



	public void setPragPoena(int pragPoena) {
		this.pragPoena = pragPoena;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
