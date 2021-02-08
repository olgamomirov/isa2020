package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Zalba;

public interface ZalbaService {

	List<Zalba> findAll();
	
	Zalba findOne(Long id);
	
	void save(Zalba zalba);
}
