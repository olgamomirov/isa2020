package tim73.isa_2020.service;

import java.util.List;
import java.util.Set;

import tim73.isa_2020.model.Lek;

public interface LekService {

	
	List<Lek> findAll();
	
	List<Lek> findByApotekaId(Long id);
	
	Set<Lek> findByNaziv(String naziv);
}
