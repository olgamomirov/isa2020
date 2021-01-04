package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.SifrarnikLekova;

public interface SifrarnikLekovaService {

	
	SifrarnikLekova getById(Long id);
	
	List<SifrarnikLekova> findAll();
	
	SifrarnikLekova findByNaziv(String naziv);
}
