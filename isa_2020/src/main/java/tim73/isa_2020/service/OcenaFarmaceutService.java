package tim73.isa_2020.service;

import tim73.isa_2020.model.OcenaFarmaceut;

public interface OcenaFarmaceutService {

	OcenaFarmaceut findByFarmaceutIdAndPacijentId(Long farmaceutId, Long pacijentId);
	
	void save (OcenaFarmaceut ocenaFarmaceut);
}
