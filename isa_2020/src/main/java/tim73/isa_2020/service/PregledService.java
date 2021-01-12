package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Pregled;

public interface PregledService {

	
	void save(Pregled pregled);

	List<Pregled> findByApotekaId(Long i);

	List<Pregled> findAll();
	
	Pregled findOne(Long id);
	
	List<Pregled> findByApotekaIdAndStatus(Long id, String status);
	
	List<Pregled> findByFarmaceutId (Long id);
}
