package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.UserTokenState;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.token.JwtAuthenticationRequest;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisnikController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	
	
	
	@Autowired
	private KorisnikService korisnikService;
	
	/*
	@PostMapping(value = "/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void login(@RequestBody Korisnik korisnik) {
		
		System.out.println(korisnik.getEmail());
		System.out.println(korisnik.getLozinka());
		
       if((korisnikService.findByEmailAndLozinka(korisnik.getEmail(), korisnik.getLozinka()))!=null) {
    	   System.out.println("korisnik se uspesno logovao jeeeeeeea!");
       }
       else {
    	   System.out.println("nema korisnika");
       }
		
		
	}
	*/
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		System.out.println("login");
		// 
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
						authenticationRequest.getLozinka()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		Korisnik user = (Korisnik) authentication.getPrincipal();
		List<Authority> authority = (List<Authority>) user.getAuthorities();
		String role = authority.get(0).getName();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, role));
	}

}
