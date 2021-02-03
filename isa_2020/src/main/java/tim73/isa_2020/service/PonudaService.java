package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Ponuda;

public interface PonudaService {

	 void save(Ponuda ponuda);
	 List<Ponuda> findAll();
	 Ponuda findOne(Long id);
}
