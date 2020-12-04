package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Apoteka;

public interface ApotekaService {

	Apoteka findById(long id);
	
	List<Apoteka> findAll();
}
