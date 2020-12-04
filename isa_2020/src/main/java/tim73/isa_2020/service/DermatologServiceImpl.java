package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.repository.DermatologRepository;

@Service
public class DermatologServiceImpl implements DermatologService {

	@Autowired
	private DermatologRepository dermatologRepository;
	
	
	
	@Override
	public Dermatolog findById(long id) {
	
		return dermatologRepository.findById(id).orElse(null);
		
	}

}
