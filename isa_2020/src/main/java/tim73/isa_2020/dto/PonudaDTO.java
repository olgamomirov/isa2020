package tim73.isa_2020.dto;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import tim73.isa_2020.model.Ponuda;

public class PonudaDTO {

    private Long id;
	
    private double ukupnaCena;
    
	private DateTime rokIsporuke;
	
	private String status;
	
	private boolean rokIstekao;
    
	public PonudaDTO(Ponuda ponuda) {
		this(ponuda.getId(), ponuda.getUkupnaCena(), ponuda.getRokIsporuke(), ponuda.getStatus(), ponuda.isRokIstekao());
	}
	
	public PonudaDTO(Long id, double ukupnaCena, String rokIsporuke, String status, boolean rokIstekao) {
		super();
		this.id = id;
		this.ukupnaCena = ukupnaCena;
		this.rokIsporuke = new DateTime(rokIsporuke);
		this.status = status;
		this.rokIstekao = rokIstekao;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public DateTime getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(DateTime rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public boolean isRokIstekao() {
		return rokIstekao;
	}

	public void setRokIstekao(boolean rokIstekao) {
		this.rokIstekao = rokIstekao;
	}
	
}
