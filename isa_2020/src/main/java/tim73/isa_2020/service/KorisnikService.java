package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.Korisnik;

public interface KorisnikService {

	Korisnik findByEmailAndLozinka(String email, String lozinka);
	Korisnik findById(Long id);
    Korisnik findByEmail(String email);
    List<Korisnik> findAll ();
}
