package tim73.isa_2020.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.AdministratorSistema;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.AuthorityService;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.ZahtevZaGodisnjiService;

@RestController
@RequestMapping(value = "/adminiSistema")
public class AdminSistemaController {

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
		
		List<Authority> a=authorityService.findByname("ROLE_SISTEM");
	
		AdministratorSistema sistemAdmin = new AdministratorSistema();
		sistemAdmin.setEmail(korisnik.getEmail());
		sistemAdmin.setIme(korisnik.getIme());
		sistemAdmin.setPrezime(korisnik.getPrezime());
		sistemAdmin.setDrzava(korisnik.getDrzava());
		sistemAdmin.setGrad(korisnik.getGrad());
		sistemAdmin.setUlica(korisnik.getUlica());
		sistemAdmin.setTelefon(korisnik.getTelefon());
		sistemAdmin.setStatus("registrovan");
		sistemAdmin.setEnabled(true);
		sistemAdmin.setLozinka(passwordEncoder.encode("123"));
		sistemAdmin.setAuthorities(a);
		
		
		
		korisnikService.save(sistemAdmin);
	}
	
}

