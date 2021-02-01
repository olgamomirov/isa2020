package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.StavkaNarudzbenice;
import tim73.isa_2020.repository.StavkaNarudzbeniceRepository;

@Service
public class StavkaNarudzbeniceServiceImpl implements StavkaNarudzbeniceService{

	@Autowired
	private StavkaNarudzbeniceRepository stavkaRepository;
	
	@Override
	public void save(StavkaNarudzbenice stavka) {
		
		stavkaRepository.save(stavka);
		
	}

	@Override
	public List<StavkaNarudzbenice> findAll() {
		
		return stavkaRepository.findAll();
	}

}
