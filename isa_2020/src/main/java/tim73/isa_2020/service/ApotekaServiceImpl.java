package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.repository.ApotekaRepository;

@Service
public class ApotekaServiceImpl implements ApotekaService {

	@Autowired
	private ApotekaRepository  apotekaRepository;
	
	
	@Override
	public Apoteka findById(long id) {
		
		return apotekaRepository.findById(id).orElse(null);
	}


	@Override
	public List<Apoteka> findAll() {
		
		return apotekaRepository.findAll();
	}

}
