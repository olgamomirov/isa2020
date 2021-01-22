package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.repository.OcenaDermatologRepository;

@Service
public class OcenaDermatologServiceImpl implements OcenaDermatologService {

	@Autowired
	private OcenaDermatologRepository ocenaDermatologRepository;

	@Override
	public OcenaDermatolog findByDermatologIdAndPacijentId(Long dermatologId, Long pacijentId) {
		return ocenaDermatologRepository.findByDermatologIdAndPacijentId(dermatologId, pacijentId);
	}

	@Override
	public void save(OcenaDermatolog ocenaDermatolog) {
		ocenaDermatologRepository.save(ocenaDermatolog);
	}

}
