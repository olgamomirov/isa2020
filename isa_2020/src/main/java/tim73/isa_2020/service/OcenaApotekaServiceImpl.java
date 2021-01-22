package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.OcenaApoteka;
import tim73.isa_2020.repository.OcenaApotekaRepository;

@Service
public class OcenaApotekaServiceImpl implements OcenaApotekaService {

	@Autowired
	private OcenaApotekaRepository ocenaApotekaRepository;

	@Override
	public List<OcenaApoteka> findByApotekaId(Long apotekaId) {
		return ocenaApotekaRepository.findByApotekaId(apotekaId);
	}

	@Override
	public OcenaApoteka findByApotekaIdAndPacijentId(Long apotekaId, Long pacijentId) {
		return ocenaApotekaRepository.findByApotekaIdAndPacijentId(apotekaId, pacijentId);
	}

	@Override
	public void save(OcenaApoteka ocenaApoteka) {
		ocenaApotekaRepository.save(ocenaApoteka);
	}
}
