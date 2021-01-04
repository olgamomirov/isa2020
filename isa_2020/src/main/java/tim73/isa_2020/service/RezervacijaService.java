package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Rezervacija;

public interface RezervacijaService {

	Rezervacija findOne(Long id);
	
	List<Rezervacija> findAll();
	
	List<Rezervacija> findByPacijentId(Long id);

	void save(Rezervacija rezervacija);
}
