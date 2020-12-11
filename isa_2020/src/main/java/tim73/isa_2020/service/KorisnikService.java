package tim73.isa_2020.service;

import tim73.isa_2020.model.Korisnik;

public interface KorisnikService {

	Korisnik findByEmailAndLozinka(String email, String lozinka);
}
