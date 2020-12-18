package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.repository.KorisnikRepository;
import tim73.isa_2020.securityService.AuthorityService;

@Service
public class KorisnikServiceImpl implements  UserDetailsService , KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;
	

	@Override
	public Korisnik findByEmailAndLozinka(String email, String lozinka) {
		return korisnikRepository.findByEmailAndLozinka(email, lozinka);
	}

	//** iz security-ja
	@Override
	public Korisnik findByEmail(String email) throws UsernameNotFoundException {
		Korisnik u = korisnikRepository.findByEmail(email);
		return u;
	}

	public Korisnik findById(Long id) throws AccessDeniedException {
		Korisnik u = korisnikRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<Korisnik> findAll() throws AccessDeniedException {
		List<Korisnik> result = korisnikRepository.findAll();
		return result;
	}
	//**

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik u = korisnikRepository.findByEmail(email);
		return u;
	}

	@Override
	public void save(Korisnik k) {
		korisnikRepository.save(k);
	}
	
}
