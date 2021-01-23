package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.OcenaApoteka;

public interface OcenaApotekaService {

	List<OcenaApoteka>  findByApotekaId(Long apotekaId);
	
	OcenaApoteka findByApotekaIdAndPacijentId(Long apotekaId, Long pacijentId);
	
	void save(OcenaApoteka ocenaApoteka);
}
