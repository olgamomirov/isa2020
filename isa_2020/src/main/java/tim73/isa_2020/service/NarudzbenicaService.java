package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Narudzbenica;

public interface NarudzbenicaService {

	
	void save(Narudzbenica narudzbenica);
	
	List<Narudzbenica> findAll();
	
}
