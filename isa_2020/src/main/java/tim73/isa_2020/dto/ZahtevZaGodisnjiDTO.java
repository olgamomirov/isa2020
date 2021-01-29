package tim73.isa_2020.dto;

import org.joda.time.Interval;

import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.ZahtevZaGodisnji;

public class ZahtevZaGodisnjiDTO {

	
	private Long id;
	
	private Interval interval;
	
	private String status;

	private String email;
	
	public ZahtevZaGodisnjiDTO(ZahtevZaGodisnji zahtev, String email) {
		this(zahtev.getId(), zahtev.getInterval(), zahtev.getStatus(), email);
	}
	
	public ZahtevZaGodisnjiDTO(Long id, String interval2, String status, String email) {
		super();
		this.interval = new Interval(interval2);
		this.status = status;
		this.id = id;
		this.email = email;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
