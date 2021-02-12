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
	
	List<Pregled> findByPacijentId (Long id);

	List<Pregled> proveraPregleda();
	
	List<Pregled> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId);
	
	List<Pregled> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId);
	
	void delete(List<Pregled> pregled);
	
	void delete(Pregled pregled);
	
}
