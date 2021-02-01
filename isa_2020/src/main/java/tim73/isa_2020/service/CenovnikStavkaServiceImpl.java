package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.repository.CenovnikStavkaRepository;

@Service
public class CenovnikStavkaServiceImpl implements CenovnikStavkaService{

	@Autowired
	private CenovnikStavkaRepository cenovnikStavkaRepository;
	
	@Override
	public void save(CenovnikStavka stavka) {
		
		cenovnikStavkaRepository.save(stavka);
		
	}

	@Override
	public List<CenovnikStavka> findAll() {
		
		return cenovnikStavkaRepository.findAll();
	}

}
