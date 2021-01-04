package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.dto.PacijentPodaciDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.UserTokenState;
import tim73.isa_2020.repository.PacijentRepository;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.token.JwtAuthenticationRequest;

@RestController
@RequestMapping(value = "/korisnici")
@CrossOrigin(origins = "http://localhost:3000")
public class KorisnikController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private PacijentService pacijentService;
	
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
		System.out.println(" status  " + user.getStatus());
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, role, user.getStatus()));
	}
	
	@GetMapping("/getPodaci")
	@PreAuthorize("hasRole('PACIJENT')") //mozda se moze iskoristiti i za ostale korisnike 
	public ResponseEntity<PacijentPodaciDTO> getPodaci(HttpServletRequest request) {

		System.out.println("podaci");
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik korisnik = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		PacijentPodaciDTO pacijentdto= new PacijentPodaciDTO(korisnik);

		
		return ResponseEntity.ok(pacijentdto);
	}
	
	@PutMapping(value="/promeniPodatke", consumes=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<PacijentPodaciDTO> setPodaci(@RequestBody PacijentPodaciDTO pacijent, HttpServletRequest request){
		
	
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik korisnik = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		
		korisnik.setIme(pacijent.getIme());
		korisnik.setPrezime(pacijent.getPrezime());
		korisnik.setUlica(pacijent.getUlica());
		korisnik.setGrad(pacijent.getGrad());
		korisnik.setDrzava(pacijent.getDrzava());
		korisnik.setTelefon(pacijent.getTelefon());
		
		userDetailsService.save(korisnik);
		System.out.println("sacuvao korisnika");
		return new ResponseEntity<PacijentPodaciDTO>(pacijent,HttpStatus.OK);
	}
	
	@GetMapping(value = "/sviPacijenti") //svi pregledani pacijenti odredjenog dermatologa...u svim apotekama...promeniti na odredjenu apoteku
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PacijentPodaciDTO>> findAll(HttpServletRequest request) {
		
        List<Pacijent> pacijenti = new ArrayList<Pacijent>();
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		
		List<PacijentPodaciDTO> pacijentiDTO= new ArrayList<>();
		
		Dermatolog d = (Dermatolog) k;
		
		for(Pregled p: d.getPregledi()) {
			if(p.getInterval().equals(null)) {
				System.out.println("nema datuma");
			}else {
			if(p.getStatus().equals("odradjen")) {
				pacijenti.add(p.getPacijent());
			}
			}
		}
		
		for(Pacijent p: pacijenti) {
			pacijentiDTO.add(new PacijentPodaciDTO(p));
		}
		
		return new ResponseEntity<List<PacijentPodaciDTO>>(pacijentiDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/dermatolog")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> findOne(HttpServletRequest request) {
		
       
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		
		Dermatolog d = (Dermatolog) k;
		
		DermatologDTO dermatologDTO = new DermatologDTO(d);
		
		
		return new ResponseEntity<DermatologDTO>(dermatologDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/farmaceut")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> findFarmaceut(HttpServletRequest request) {
		
       
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		
		Farmaceut f = (Farmaceut) k;
		
		FarmaceutDTO farmaceutDTO = new FarmaceutDTO(f);
		
		
		return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
  	@ResponseBody
  	public ResponseEntity<List<PacijentPodaciDTO>> getPacijenti(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime){
  		List<Pacijent> pacijenti = pacijentService.searchByImeLikeOrPrezimeLike(ime, prezime);
  	
  		List<PacijentPodaciDTO> pacijentiDTO= new ArrayList<>();
  		
  			
  			for(Pacijent p: pacijenti)
  			{
  				pacijentiDTO.add(new PacijentPodaciDTO(p));
  			}
  		
  		return new ResponseEntity<List<PacijentPodaciDTO>>(pacijentiDTO, HttpStatus.OK);
  	}

	
}
