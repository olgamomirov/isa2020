package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.OcenaSifrarnikLekova;
import tim73.isa_2020.repository.OcenaSifrarnikLekovaRepository;

@Service
public class OcenaSifrarnikLekovaServiceImpl implements OcenaSifrarnikLekovaService {

	@Autowired
	private OcenaSifrarnikLekovaRepository ocenaSifrarnikLekovaRepository;
	
	@Override
	public OcenaSifrarnikLekova findBySifrarnikLekovaIdAndPacijentId(Long sifrarnikLekovaId, Long pacijentId) {
		return ocenaSifrarnikLekovaRepository.findBySifrarnikLekovaIdAndPacijentId(sifrarnikLekovaId, pacijentId);
	}

	@Override
	public List<OcenaSifrarnikLekova> findBySifrarnikLekovaId(Long sifrarnikLekovaId) {
		return ocenaSifrarnikLekovaRepository.findBySifrarnikLekovaId(sifrarnikLekovaId);
	}

	@Override
	public void save(OcenaSifrarnikLekova ocenaSifrarnikLekova) {
		ocenaSifrarnikLekovaRepository.save(ocenaSifrarnikLekova);
	}

}
