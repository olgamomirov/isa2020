package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import tim73.isa_2020.controller.DermatologController.StatusBody;
import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.dto.LozinkaDTO;
import tim73.isa_2020.dto.PacijentPodaciDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.UserTokenState;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.repository.PacijentRepository;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.ZahtevZaGodisnjiService;
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

	@Autowired
	private PregledService pregledService;

	@Autowired
	private EmailService mailService;

	@Autowired
	private ZahtevZaGodisnjiService zahtevGodisnjiService;

	@Autowired
	private ApotekaService apotekaService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * @PostMapping(value = "/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	 * public void login(@RequestBody Korisnik korisnik) {
	 * 
	 * System.out.println(korisnik.getEmail());
	 * System.out.println(korisnik.getLozinka());
	 * 
	 * if((korisnikService.findByEmailAndLozinka(korisnik.getEmail(),
	 * korisnik.getLozinka()))!=null) {
	 * System.out.println("korisnik se uspesno logovao jeeeeeeea!"); } else {
	 * System.out.println("nema korisnika"); }
	 * 
	 * 
	 * }
	 */

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

		System.out.println("login");
		//
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), authenticationRequest.getLozinka()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
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
	@PreAuthorize("hasRole('PACIJENT')or hasRole('ADMINISTRATOR')") // mozda se moze iskoristiti i za ostale korisnike
	public ResponseEntity<PacijentPodaciDTO> getPodaci(HttpServletRequest request) {

		System.out.println("podaci");
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		PacijentPodaciDTO korisnikDTO;

		for (GrantedAuthority a : this.userDetailsService.loadUserByUsername(username).getAuthorities()) {
			System.out.println(a.getAuthority());
			if (a.getAuthority().equals("ROLE_PACIJENT")) {

				Pacijent korisnik = (Pacijent) this.userDetailsService.loadUserByUsername(username);
				System.out.println(korisnik.getPenal());
				korisnikDTO = new PacijentPodaciDTO(korisnik, korisnik.getPenal(),korisnik.getLoyaltyProgram().getKategorijaKorisnika());
				return ResponseEntity.ok(korisnikDTO);

			} else if (a.getAuthority().equals("ROLE_ADMINISTRATOR")) {
				AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService
						.loadUserByUsername(username);
				System.out.println("ispis iz getPodaci: "+korisnik.getDrzava()+" "+korisnik.getStatus());

				korisnikDTO = new PacijentPodaciDTO(korisnik);
				return ResponseEntity.ok(korisnikDTO);
			}
		}

		return null;
	}

	@PutMapping(value = "/promeniPodatke", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PACIJENT') or hasRole('ADMINISTRATOR')")
	public ResponseEntity<PacijentPodaciDTO> setPodaci(@RequestBody PacijentPodaciDTO pacijent,
			HttpServletRequest request) {

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
		return new ResponseEntity<PacijentPodaciDTO>(pacijent, HttpStatus.OK);
	}

	@GetMapping(value = "/sviPregledaniPacijenti") // svi pregledani pacijenti odredjenog dermatologa...u svim
													// apotekama...
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PacijentPodaciDTO>> findAll(HttpServletRequest request) {

		List<Pacijent> pacijenti = new ArrayList<Pacijent>();

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		List<PacijentPodaciDTO> pacijentiDTO = new ArrayList<>();

		Dermatolog d = (Dermatolog) k;

		for (Pregled p : d.getPregledi()) {
			if (p.getInterval().equals(null)) {
				System.out.println("nema datuma");
			} else {
				if (p.getStatus().equals("odradjen")) {
					pacijenti.add(p.getPacijent());
				}
			}
		}

		for (Pacijent p : pacijenti) {
			pacijentiDTO.add(new PacijentPodaciDTO(p));
		}

		return new ResponseEntity<List<PacijentPodaciDTO>>(pacijentiDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/sviPregledaniPacijentiFarmaceuta") // svi pregledani pacijenti odredjenog dermatologa...u svim
																// apotekama...
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<List<PacijentPodaciDTO>> findAllFromFarmaceut(HttpServletRequest request) {

		List<Pacijent> pacijenti = new ArrayList<Pacijent>();

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		List<PacijentPodaciDTO> pacijentiDTO = new ArrayList<>();

		Farmaceut f = (Farmaceut) k;

		for (Pregled p : f.getPregledi()) {
			if (p.getInterval().equals(null)) {
				System.out.println("nema datuma");
			} else {
				if (p.getStatus().equals("odradjen")) {
					pacijenti.add(p.getPacijent());
				}
			}
		}

		for (Pacijent p : pacijenti) {
			pacijentiDTO.add(new PacijentPodaciDTO(p));
		}

		return new ResponseEntity<List<PacijentPodaciDTO>>(pacijentiDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/sviPacijenti") // svi pregledani pacijenti odredjenog dermatologa...u svim apotekama...i
											// farmaceuta
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<List<PacijentPodaciDTO>> findAll1(HttpServletRequest request) {

		List<Korisnik> korisnici = korisnikService.findAll();

		List<Korisnik> pacijenti = new ArrayList<Korisnik>();

		for (Korisnik k : korisnici) {
			for (GrantedAuthority a : k.getAuthorities()) {
				if (a.getAuthority().equals("ROLE_PACIJENT")) {
					pacijenti.add((Pacijent) k);
				}
			}
		}
		List<PacijentPodaciDTO> dto = new ArrayList<PacijentPodaciDTO>();
		for (Korisnik k : pacijenti) {
			dto.add(new PacijentPodaciDTO(k));
		}

		return new ResponseEntity<List<PacijentPodaciDTO>>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/pacijent")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<PacijentPodaciDTO> getOne(@RequestParam(value = "email") String email,
			HttpServletRequest request) {

		Pacijent p = (Pacijent) korisnikService.findByEmail(email);
		PacijentPodaciDTO dto = new PacijentPodaciDTO(p);

		return new ResponseEntity<PacijentPodaciDTO>(dto, HttpStatus.OK);
	}

	static class PenalBody {
		public String email;
		public Long id;
	}

	@RequestMapping(value = "/penal", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<PacijentPodaciDTO> setPenal(@RequestBody PenalBody penal, HttpServletRequest request) {

		Pacijent p = (Pacijent) korisnikService.findByEmail(penal.email);
		p.setPenal(p.getPenal() + 1);
		korisnikService.save(p);
		PacijentPodaciDTO dto = new PacijentPodaciDTO(p);

		Pregled pregled = pregledService.findOne(penal.id);

		pregled.setStatus("default");
		pregled.setPacijent(null);
		pregledService.save(pregled);

		return new ResponseEntity<PacijentPodaciDTO>(dto, HttpStatus.OK);
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

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PacijentPodaciDTO>> getPacijenti(@RequestParam("ime") String ime,
			@RequestParam("prezime") String prezime) {
		List<Pacijent> pacijenti = pacijentService.searchByImeLikeOrPrezimeLike(ime, prezime);

		List<PacijentPodaciDTO> pacijentiDTO = new ArrayList<>();

		for (Pacijent p : pacijenti) {
			pacijentiDTO.add(new PacijentPodaciDTO(p));
		}

		return new ResponseEntity<List<PacijentPodaciDTO>>(pacijentiDTO, HttpStatus.OK);
	}

	static class Godisnji {
		public String pocetak;
		public String kraj;
	}

	@RequestMapping(value = "/godisnji/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('FARMACEUT')")
	public String setGodisnji(@RequestBody Godisnji godisnji, HttpServletRequest request)
			throws MailException, InterruptedException {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		String interval = godisnji.pocetak + ":00.000+01:00" + "/" + godisnji.kraj + ":00.000+01:00";

		Farmaceut f = (Farmaceut) k;
		ZahtevZaGodisnji zahtev = new ZahtevZaGodisnji(interval, "neodobren", "", null, f, f.getApoteka());
		zahtevGodisnjiService.save(zahtev);

		/*
		 * Set<AdministratorApoteke> admini = a.getAdministratorApoteke(); if(!flag) {
		 * String email = null; for(AdministratorApoteke admin: admini) { email =
		 * admin.getEmail(); //salje prvom adminu kog nadje break; }
		 */
		mailService.sendSimpleMessage("violetamarceta1995@gmail.com",
				"Zahtev za godisnji odmor od lekara: " + k.getIme() + " " + k.getPrezime(),
				"Od: " + godisnji.pocetak.toString() + "do: " + godisnji.kraj.toString());
		// }

		return k.getIme();
	}

	static class GodisnjiDermatolog {
		public String pocetak;
		public String kraj;
		public Long apotekaId;
	}

	@RequestMapping(value = "/godisnji/addDermatolog", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public String setGodisnjiDermatolog(@RequestBody GodisnjiDermatolog godisnji, HttpServletRequest request)
			throws MailException, InterruptedException {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Apoteka a = apotekaService.findById(godisnji.apotekaId);

		String interval = godisnji.pocetak + ":00.000+01:00" + "/" + godisnji.kraj + ":00.000+01:00";

		Dermatolog d = (Dermatolog) k;
		// ZahtevZaGodisnji zahtev = new ZahtevZaGodisnji(interval, d, null, );

		ZahtevZaGodisnji zahtev = new ZahtevZaGodisnji(interval, "neodobren", "", d, null, a);
		zahtevGodisnjiService.save(zahtev);

		/*
		 * Set<AdministratorApoteke> admini = a.getAdministratorApoteke(); if(!flag) {
		 * String email = null; for(AdministratorApoteke admin: admini) { email =
		 * admin.getEmail(); //salje prvom adminu kog nadje break; }
		 */
		mailService.sendSimpleMessage("violetamarceta1995@gmail.com",
				"Zahtev za godisnji odmor od lekara: " + k.getIme() + " " + k.getPrezime(),
				"Od: " + godisnji.pocetak.toString() + "do: " + godisnji.kraj.toString());
		// }

		return k.getIme();
	}
	@PutMapping(value = "/promenaStatusa")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> promenaStatusa(@RequestBody String status, HttpServletRequest request) {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik korisnik = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		korisnik.setStatus(status);
		korisnikService.save(korisnik);
		return new ResponseEntity<String>(korisnik.getStatus(), HttpStatus.OK);

	}
	@PutMapping(value = "/promenaLozinke", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> promenaLozinke(@RequestBody LozinkaDTO lozinka, HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik korisnik = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		if (BCrypt.checkpw(lozinka.getStara(), korisnik.getLozinka())) {
			korisnik.setLozinka(passwordEncoder.encode(lozinka.getNova()));
			korisnikService.save(korisnik);
			return ResponseEntity.ok("Uspesno ste promenili lozinku");
		} else {
			return ResponseEntity.badRequest().body("Pogresna stara lozinka");
		}
	}

	
	

	
}
