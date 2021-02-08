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

import tim73.isa_2020.dto.AdminApotekaDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.AdministratorSistema;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.securityService.AuthorityService;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;

@RestController
@RequestMapping(value = "/adminiApoteka")
public class AdminApotekeController {

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
	
	@Autowired
	private ApotekaService apotekaService;
	
	@RequestMapping(value = "/registruj", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('SISTEM')") //administrator sistema dodaje novog administratora apoteke
	public void registruj(@RequestBody AdminApotekaDTO korisnik, HttpServletRequest request) {
		
		List<Authority> a=authorityService.findByname("ROLE_ADMINISTRATOR");
	
		Apoteka apoteka = apotekaService.findById(korisnik.getApotekaId());
		
		AdministratorApoteke adminApoteka = new AdministratorApoteke();
		adminApoteka.setEmail(korisnik.getEmail());
		adminApoteka.setIme(korisnik.getIme());
		adminApoteka.setPrezime(korisnik.getPrezime());
		adminApoteka.setDrzava(korisnik.getDrzava());
		adminApoteka.setGrad(korisnik.getGrad());
		adminApoteka.setUlica(korisnik.getUlica());
		adminApoteka.setTelefon(korisnik.getTelefon());
		adminApoteka.setStatus("registrovan");
		adminApoteka.setEnabled(true);
		adminApoteka.setLozinka(passwordEncoder.encode("123"));
		adminApoteka.setAuthorities(a);
		adminApoteka.setApoteka(apoteka);
		
		
		korisnikService.save(adminApoteka );
	}
	
}
