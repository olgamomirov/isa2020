package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.UpitZaLek;

public interface UpitZaLekService {

	
	void save(UpitZaLek upit);
	
	List<UpitZaLek> findAll();
}
