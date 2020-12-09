package tim73.isa_2020.service;

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

}
