package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public Korisnik findByEmailAndLozinka(String email, String lozinka) {
		return korisnikRepository.findByEmailAndLozinka(email, lozinka);
	}

}
