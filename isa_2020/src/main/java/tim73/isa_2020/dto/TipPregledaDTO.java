package tim73.isa_2020.dto;

import tim73.isa_2020.model.TipPregleda;

public class TipPregledaDTO {

    private Long id;
	
	private String tip;
	
	private double cena;
	
	
	
	public TipPregledaDTO() {
		super();
	}

	public TipPregledaDTO(TipPregleda tip) {
		this(tip.getId(), tip.getTip(), tip.getCena());
	}

	public TipPregledaDTO(Long id, String tip, double cena) {
		super();
		this.id = id;
		this.tip = tip;
		this.cena = cena;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}
	
}
