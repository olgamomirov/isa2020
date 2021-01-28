package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.ZahtevZaGodisnji;

public interface ZahtevZaGodisnjiService {

	
	void save(ZahtevZaGodisnji zahtev);
	
	List<ZahtevZaGodisnji> findAll();
	
	ZahtevZaGodisnji findOne(Long id);
	
	List<ZahtevZaGodisnji> findByApotekaId(Long id);
	
}
