package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.RadnoVreme;

public interface RadnoVremeService {

	void save(RadnoVreme rv);
	
	List<RadnoVreme> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId);
	
	List<RadnoVreme> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId);

	void remove(RadnoVreme rv);
}
