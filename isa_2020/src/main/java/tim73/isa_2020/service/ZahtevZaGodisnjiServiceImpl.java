package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.repository.ZahtevZaGodisnjiRepository;

@Service
public class ZahtevZaGodisnjiServiceImpl implements ZahtevZaGodisnjiService{

	@Autowired
	private ZahtevZaGodisnjiRepository zahtevRepository;
	
	@Override
	public void save(ZahtevZaGodisnji zahtev) {
		
		zahtevRepository.save(zahtev);
	}

	@Override
	public List<ZahtevZaGodisnji> findAll() {
		
		return zahtevRepository.findAll();
	}

	@Override
	public ZahtevZaGodisnji findOne(Long id) {
		
		return zahtevRepository.findById(id).orElse(null);
	}

	@Override
	public List<ZahtevZaGodisnji> findByApotekaId(Long id) {
		// TODO Auto-generated method stub
		return zahtevRepository.findByApotekaId(id);
	}

}
