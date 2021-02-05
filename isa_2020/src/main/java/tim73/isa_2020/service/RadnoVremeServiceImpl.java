package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.repository.RadnoVremeRepository;

@Service
public class RadnoVremeServiceImpl implements RadnoVremeService {

	@Autowired 
	private RadnoVremeRepository radnoVremeRepository;
	
	@Override
	public void save(RadnoVreme rv) {
		radnoVremeRepository.save(rv);

	}

	@Override
	public List<RadnoVreme> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId) {
		return radnoVremeRepository.findByFarmaceutIdAndApotekaId(farmaceutId, apotekaId);
	}

	@Override
	public List<RadnoVreme> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId) {
		return radnoVremeRepository.findByDermatologIdAndApotekaId(dermatologId, apotekaId);
	}

	@Override
	public void remove(RadnoVreme rv) {
		radnoVremeRepository.delete(rv);
	}

}
