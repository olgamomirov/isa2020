package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.OcenaFarmaceut;
import tim73.isa_2020.repository.OcenaFarmaceutRepository;

@Service
public class OcenaFarmaceutServiceImpl implements OcenaFarmaceutService {

	@Autowired
	private OcenaFarmaceutRepository ocenaFarmaceutRepository;
	
	@Override
	public OcenaFarmaceut findByFarmaceutIdAndPacijentId(Long farmaceutId, Long pacijentId) {
		return ocenaFarmaceutRepository.findByFarmaceutIdAndPacijentId(farmaceutId, pacijentId);
	}

	@Override
	public void save(OcenaFarmaceut ocenaFarmaceut) {
		ocenaFarmaceutRepository.save(ocenaFarmaceut);
	}

}
