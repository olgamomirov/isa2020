package tim73.isa_2020.service;

import tim73.isa_2020.model.Dermatolog;


public interface DermatologService {

	Dermatolog findById(long id);
	
	void save(Dermatolog dermatolog);
}
