package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.repository.AkcijaPromocijaRepository;

@Service
public class AkcijaPromocijaServiceImpl implements AkcijaPromocijaService {

	@Autowired
	private AkcijaPromocijaRepository akcijaPromocijaRepository;
	
	@Override
	public void save(AkcijaPromocija akcijaPromocija) {
		akcijaPromocijaRepository.save(akcijaPromocija);
	}

}
