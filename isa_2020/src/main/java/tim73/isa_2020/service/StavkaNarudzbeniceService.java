package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.StavkaNarudzbenice;

public interface StavkaNarudzbeniceService {

	void save(StavkaNarudzbenice stavka);
	
	List<StavkaNarudzbenice> findAll();
}
