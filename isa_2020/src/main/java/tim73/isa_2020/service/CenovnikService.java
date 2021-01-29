package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Cenovnik;

public interface CenovnikService {

	Cenovnik findOne(Long id);
	
	List<Cenovnik> findAll();
	
	void save(Cenovnik cenovnik);
}
