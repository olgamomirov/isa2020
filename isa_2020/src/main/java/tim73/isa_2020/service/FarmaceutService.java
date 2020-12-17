package tim73.isa_2020.service;

import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Farmaceut;


public interface FarmaceutService {

	
	Farmaceut findOne(Long id);
	
}
