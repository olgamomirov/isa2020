package tim73.isa_2020.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Lek;
import tim73.isa_2020.repository.LekRepository;

@Service
public class LekServiceImpl implements LekService {

	@Autowired
	private LekRepository lekRepository;
	
	@Override
	public List<Lek> findAll() {
		// TODO Auto-generated method stub
		return lekRepository.findAll();
	}

	@Override
	public List<Lek> findByApotekaId(Long id) {
		// TODO Auto-generated method stub
		return lekRepository.findByApotekaId(id);
	}

	@Override
	public void save(Lek lek) {
		
		 lekRepository.save(lek);
		
	}

	@Override
	public List<Lek> findBySifrarnikLekova(Long id) {
		return lekRepository.findBySifrarnikLekovaId(id);
	}

	@Override
	public Lek findBySifrarnikLekovaIdAndApotekaId(Long sifrarnikId, Long apotekaId) {
		return lekRepository.findBySifrarnikLekovaIdAndApotekaId(sifrarnikId, apotekaId);
	}

	@Override
	public Lek findById(Long id) {
		return lekRepository.getOne(id);
	}

	

}
