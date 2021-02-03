package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Dobavljac;
import tim73.isa_2020.repository.DobavljacRepository;

@Service
public class DobavljacServiceImpl implements DobavljacService{

	@Autowired
	private DobavljacRepository dobavljacRepository;
	
	@Override
	public Dobavljac findOne(Long id) {
		
		return dobavljacRepository.findById(id).orElse(null);
	}

}
