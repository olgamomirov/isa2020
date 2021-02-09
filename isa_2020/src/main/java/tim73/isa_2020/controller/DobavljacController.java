package tim73.isa_2020.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.model.AdministratorSistema;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dobavljac;
import tim73.isa_2020.securityService.AuthorityService;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;

@RestController
@RequestMapping(value = "/dobavljaci")
public class DobavljacController {


	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/registruj", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('SISTEM')") //administrator sistema dodaje novog administratora sistema 
	public void registruj(@RequestBody FarmaceutDTO korisnik, HttpServletRequest request) {
		
		List<Authority> a=authorityService.findByname("ROLE_DOBAVLJAC");
	
		Dobavljac dobavljac = new Dobavljac();
		dobavljac.setEmail(korisnik.getEmail());
		dobavljac.setIme(korisnik.getIme());
		dobavljac.setPrezime(korisnik.getPrezime());
		dobavljac.setDrzava(korisnik.getDrzava());
		dobavljac.setGrad(korisnik.getGrad());
		dobavljac.setUlica(korisnik.getUlica());
		dobavljac.setTelefon(korisnik.getTelefon());
		dobavljac.setStatus("registrovan");
		dobavljac.setEnabled(true);
		dobavljac.setLozinka(passwordEncoder.encode("123"));
		dobavljac.setAuthorities(a);
		
		
		
		korisnikService.save(dobavljac);
	}
}
