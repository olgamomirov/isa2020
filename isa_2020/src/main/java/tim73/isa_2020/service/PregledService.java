package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Pregled;

public interface PregledService {

	
	void save(Pregled pregled);

	List<Pregled> findByApotekaId(Long id);
}
