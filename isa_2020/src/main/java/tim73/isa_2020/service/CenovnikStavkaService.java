package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.CenovnikStavka;

public interface CenovnikStavkaService {

	void save(CenovnikStavka stavka);
	
	List<CenovnikStavka> findAll();
} 
