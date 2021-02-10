package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.DermatologController.PasswordChanger;
import tim73.isa_2020.controller.DermatologController.StatusBody;
import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.OcenaFarmaceut;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.AuthorityService;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.OcenaFarmaceutService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RadnoVremeService;

@RestController
@RequestMapping(value = "/farmaceuti")
@CrossOrigin(origins = "http://localhost:3000")

public class FarmaceutiController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private FarmaceutService farmaceutService;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PregledService pregledService;

	@Autowired
	private OcenaFarmaceutService ocenaFarmaceutService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private RadnoVremeService radnoVremeService;


	@RequestMapping(method = RequestMethod.POST, value = "/changeData", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changeData(@RequestBody Korisnik korisnik, HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Korisnik userEdited = ulogovan;
		if (korisnik.getIme() != "") {
			userEdited.setIme(korisnik.getIme());
		}
		if (korisnik.getPrezime() != "") {
			userEdited.setPrezime(korisnik.getPrezime());
		}
		if (korisnik.getEmail() != "") {
			userEdited.setEmail(korisnik.getEmail());
		}
		if (korisnik.getUlica() != "") {
			userEdited.setUlica(korisnik.getUlica());
		}
		if (korisnik.getGrad() != "") {
			userEdited.setGrad(korisnik.getGrad());
		}
		if (korisnik.getDrzava() != "") {
			userEdited.setDrzava(korisnik.getDrzava());
		}
		if (korisnik.getTelefon() != "") {
			userEdited.setTelefon(korisnik.getTelefon());
		}
		korisnikService.save(userEdited);

		Farmaceut f = (Farmaceut) userEdited;

		FarmaceutDTO dto = new FarmaceutDTO(f);

		return new ResponseEntity<FarmaceutDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getData")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> getData(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		FarmaceutDTO farmaceutDTO = new FarmaceutDTO(ulogovan);

		return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changePassword(@RequestBody PasswordChanger passwordChanger,
			HttpServletRequest request) {
		// Ocitavamo trenutno ulogovanog korisnika
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		;
		user.setStatus("ulogovan");
		FarmaceutDTO farmaceutDTO = new FarmaceutDTO(user);

		if (BCrypt.checkpw(passwordChanger.oldPassword, user.getLozinka())) {

			user.setLozinka(passwordEncoder.encode(passwordChanger.newPassword));
			korisnikService.save(user);
			return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.OK);
		} else {

			System.out.println("ne!");
			return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.NOT_MODIFIED);
		}

	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}

	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changeStatus(@RequestBody StatusBody status, HttpServletRequest request) {
		// Ocitavamo trenutno ulogovanog korisnika
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		List<Authority> authority = (List<Authority>) user.getAuthorities();

		// new UserTokenState(token, tokenUtils.getExpiredIn(),
		// authority.get(0).getName(), "ulogovan");

		System.out.println(status.statusNovi + " sta cita?");
		String status1 = status.statusNovi;
		user.setStatus(status1);

		korisnikService.save(user);

		System.out.println(user.getStatus() + " kakooo" + user.getIme() + " " + user.getTelefon());

		Farmaceut f = (Farmaceut) user;

		FarmaceutDTO dto = new FarmaceutDTO(f);

		return new ResponseEntity<FarmaceutDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/slobodni/{vreme}/{idApoteke}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> slobodniFarmaceuti(@PathVariable String vreme, @PathVariable Long idApoteke, HttpServletRequest request) {
		String datum = vreme.split("T")[0];
		String vremeString = vreme.split("T")[1];
		System.out.println(datum);
		System.out.println(vremeString);

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent pacijent = (Pacijent) this.userDetailsService.loadUserByUsername(username);
		
		DateTime vremePregleda = new DateTime(Integer.parseInt(datum.split("-")[0]),
				Integer.parseInt(datum.split("-")[1]), Integer.parseInt(datum.split("-")[2]),
				Integer.parseInt(vremeString.split(":")[0]), Integer.parseInt(vremeString.split(":")[1]));
		Interval noviPregled = new Interval(vremePregleda, new Period(1800000)); // pola sata za pregled

		ArrayList<Farmaceut> sviFarmaceutiIzApoteke = (ArrayList<Farmaceut>) farmaceutService
				.findByApotekaId(idApoteke);
		ArrayList<LekarDTO> slobodniFarmaceuti = new ArrayList<LekarDTO>();
		for (Farmaceut farmaceut : sviFarmaceutiIzApoteke) {
			int slobodanPregled = 0;
			int slobodanUTokuRadnogVremena = 0;
			double cena=0;
			double cenaSaPopustom=0;
			for (Pregled pregled : farmaceut.getPregledi()) {
				Interval interval = new Interval(pregled.getInterval());
				if (pregled != null && pregled.getApoteka().getId().equals(idApoteke)
						&& (interval.contains(noviPregled)) || interval.overlaps(noviPregled)) {
					slobodanPregled++;
				}
				cena=pregled.getTip().getCena();
			}
			for (RadnoVreme rv : farmaceut.getRadnoVreme()) {
				Interval interval = new Interval(rv.getInterval());
				if (rv != null && rv.getApoteka().getId().equals(idApoteke) && interval.contains(noviPregled)) {
					slobodanUTokuRadnogVremena++;

				}
			}

			if (slobodanPregled == 0 && slobodanUTokuRadnogVremena != 0) {
				
				
				double ocena=0;
				double brOcena=0;
				if(!farmaceut.getOceneFarmaceuta().isEmpty()) {
					for(OcenaFarmaceut of:farmaceut.getOceneFarmaceuta()) {
						ocena+=of.getVrednost();
						brOcena++;
					}
					ocena=ocena/brOcena;
				}
				
				cenaSaPopustom=cena*((100-pacijent.getLoyaltyProgram().getPopust())/100);
				
				slobodniFarmaceuti.add(new LekarDTO(farmaceut.getId(),
						farmaceut.getIme() + " " + farmaceut.getPrezime(), "farmaceut", ocena,cena,cenaSaPopustom));
			}
		}
		return new ResponseEntity<List<LekarDTO>>(slobodniFarmaceuti, HttpStatus.OK);
	}

	// pacijentu se nude farmaceuti kod kojih je bar jednom imao pregled
	@RequestMapping(value = "/farmaceutiZaOcenjivanje", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> farmaceutiKojiSemoguOceniti(HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		List<LekarDTO> farmaceuti = new ArrayList<LekarDTO>();

		for (Pregled pregled : pregledService.findByPacijentId(p.getId())) {
			if (pregled.getStatus().equals("odradjen") && pregled.getFarmaceut() != null) {
				LekarDTO farmaceut;
				OcenaFarmaceut ocena = ocenaFarmaceutService
						.findByFarmaceutIdAndPacijentId(pregled.getFarmaceut().getId(), p.getId());
				if (ocena != null) {
					farmaceut = new LekarDTO(pregled.getFarmaceut().getId(),
							pregled.getFarmaceut().getIme()+" " + pregled.getFarmaceut().getPrezime(), "farmaceut",
							ocena.getVrednost());
				} else {
					farmaceut = new LekarDTO(pregled.getFarmaceut().getId(),
							pregled.getFarmaceut().getIme() +" "+ pregled.getFarmaceut().getPrezime(), "farmaceut", 0);
				}
				if (!farmaceuti.contains(farmaceut)) {
					farmaceuti.add(farmaceut);
				}
			}
		}
		return new ResponseEntity<List<LekarDTO>>(farmaceuti, HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekarDTO>> pretragaFarmaceuta(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime,HttpServletRequest request){
		

		List<Farmaceut> farmaceuti=farmaceutService.findByImeIPrezime(ime, prezime);
		List<LekarDTO> farmaceutiDTO= new ArrayList<LekarDTO>();
		for(Farmaceut farmaceut:farmaceuti) {
			double ocena=0;
			double brOcena=0;
			if(!farmaceut.getOceneFarmaceuta().isEmpty()) {
				for(OcenaFarmaceut of:farmaceut.getOceneFarmaceuta()) {
					ocena+=of.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			farmaceutiDTO.add(new LekarDTO(farmaceut.getId(), farmaceut.getIme()+" "+farmaceut.getPrezime(), "farmaceut", ocena, farmaceut.getApoteka().getNaziv()));
		}
		return new ResponseEntity<List<LekarDTO>>(farmaceutiDTO, HttpStatus.OK);
	}
	@RequestMapping(value = "/farmaceuti", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekarDTO>> farmaceuti(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<LekarDTO> farmaceutiDTO= new ArrayList<LekarDTO>();
		
		Apoteka a=admin.getApoteka();
		
		for(Farmaceut farmaceut:a.getFarmaceuti()) {
			
			double ocena=0;
			double brOcena=0;
			if(!farmaceut.getOceneFarmaceuta().isEmpty()) {
				for(OcenaFarmaceut of:farmaceut.getOceneFarmaceuta()) {
					ocena+=of.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			farmaceutiDTO.add(new LekarDTO(farmaceut.getId(), farmaceut.getIme()+" "+farmaceut.getPrezime(), "farmaceut", ocena, farmaceut.getApoteka().getNaziv()));
		}
		return new ResponseEntity<List<LekarDTO>>(farmaceutiDTO, HttpStatus.OK);
	}
	
	//administrator apoteke zaposljava novog farmaceuta
		@RequestMapping(value = "/registruj", method = RequestMethod.POST, consumes = "application/json")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public void registruj(@RequestBody FarmaceutDTO korisnik, HttpServletRequest request) {
			
			String token = tokenUtils.getToken(request);
			String username = this.tokenUtils.getUsernameFromToken(token);
			AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
			
			List<Authority> a=authorityService.findByname("ROLE_FARMACEUT");
			Set<RadnoVreme> radnoVreme= new HashSet<RadnoVreme>();
			
			Farmaceut farmaceut= new Farmaceut();
			farmaceut.setEmail(korisnik.getEmail());
			farmaceut.setIme(korisnik.getIme());
			farmaceut.setPrezime(korisnik.getPrezime());
			farmaceut.setDrzava(korisnik.getDrzava());
			farmaceut.setGrad(korisnik.getGrad());
			farmaceut.setUlica(korisnik.getUlica());
			farmaceut.setTelefon(korisnik.getTelefon());
			farmaceut.setStatus("registrovan");
			farmaceut.setEnabled(true);
			farmaceut.setLozinka(passwordEncoder.encode("123"));
			farmaceut.setAuthorities(a);
			farmaceut.setApoteka(admin.getApoteka());
			
			for(String s:korisnik.getRadnoVreme()) {
				String interval = s.split(",")[0] + ":00.000+01:00" + "/" +s.split(",")[1] + ":00.000+01:00";
				RadnoVreme rv=new RadnoVreme(interval);
				rv.setFarmaceut(farmaceut);
				rv.setApoteka(admin.getApoteka());
				radnoVreme.add(rv);
			}
			
			farmaceut.setRadnoVreme(radnoVreme);
			
			korisnikService.save(farmaceut);
		}
		
		@PutMapping(value = "/otpusti/{id}")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<String> otpusti(@PathVariable Long id, HttpServletRequest request) {
			System.out.println(id);
			String token = tokenUtils.getToken(request);
			String username = this.tokenUtils.getUsernameFromToken(token);
			AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka=admin.getApoteka();
		  Farmaceut f=farmaceutService.findOne(id); 
		  if(!pregledService.findByFarmaceutIdAndApotekaId(id, apoteka.getId()).isEmpty()) {
			  for (Pregled pregled: pregledService.findByFarmaceutIdAndApotekaId(id, apoteka.getId())) {
				  if(pregled.getStatus().equals("rezervisan")) {
					  return ResponseEntity.badRequest().body("Farmaceut ima zakazane preglede");
				  }
			  }
		  }
		 f.setApoteka(null);
		 
		 for(RadnoVreme rv: radnoVremeService.findByFarmaceutIdAndApotekaId(id, apoteka.getId())) {
			 f.getRadnoVreme().remove(rv);
			 radnoVremeService.remove(rv);
			 
		 }
		 
		 korisnikService.save(f);
		 return new ResponseEntity<String>("Farmaceut je otpusten!", HttpStatus.OK);
			
		}
		
		
}		


