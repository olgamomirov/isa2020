package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.TipPregleda;

public interface TipPregledaService {

	void save(TipPregleda tip);
	
	List<TipPregleda> findAll();
	
	List<TipPregleda> findByTip(String tip);
	
	TipPregleda findByTipAndApotekaId (String tip, Long idApoteke);
	
}
