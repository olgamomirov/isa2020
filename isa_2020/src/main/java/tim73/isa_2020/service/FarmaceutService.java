package tim73.isa_2020.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;


public interface FarmaceutService {

	
	Farmaceut findOne(Long id);
	
	List<Farmaceut> findByApotekaId(Long id);
	
	List<Farmaceut> findByImeIPrezime(String ime, String prezime);
	
	List<Farmaceut> findAll();


}
