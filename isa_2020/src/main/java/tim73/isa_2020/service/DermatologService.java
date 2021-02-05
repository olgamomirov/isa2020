package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Dermatolog;


public interface DermatologService {

	Dermatolog findById(long id);
	
	void save(Dermatolog dermatolog);
	
	List<Dermatolog> findByImeIPrezime(String ime, String prezime);
	
	List<Dermatolog> findAll();
}
