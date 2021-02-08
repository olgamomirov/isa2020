package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.OcenaApoteka;
import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.OcenaFarmaceut;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.OcenaApotekaService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RezervacijaService;
import tim73.isa_2020.service.SifrarnikLekovaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/a")
public class ApotekaController {

	@Autowired
	private ApotekaService apotekaService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private SifrarnikLekovaService sifrarnikService;

	@Autowired
	private LekService lekService;

	@Autowired
	private PregledService pregledService;

	@Autowired
	private RezervacijaService rezervacijaService;

	@Autowired
	private OcenaApotekaService ocenaApotekaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ApotekaDTO> findById(@PathVariable long id) {
		return new ResponseEntity<>(new ApotekaDTO(apotekaService.findById(id)), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<ApotekaDTO>> findAll() {

		List<Apoteka> apoteke = apotekaService.findAll();

		List<ApotekaDTO> apotekeDTO = new ArrayList<>();
		for (Apoteka a : apoteke) {
			double ocena = 0;
			double brojOcena = 0;
			for (OcenaApoteka oa : ocenaApotekaService.findByApotekaId(a.getId())) {
				ocena += oa.getVrednost();
				brojOcena++;
			}
			ocena = ocena / brojOcena;
			apotekeDTO.add(new ApotekaDTO(a, ocena));
		}

		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/sve") // sve apoteke u kojima DERMATOLOG radi
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<ApotekaDTO>> findAllD(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Dermatolog d = (Dermatolog) k;

		List<Apoteka> apoteke = new ArrayList<>();

		List<ApotekaDTO> apotekeDTO = new ArrayList<>();

		for (Apoteka a : d.getApoteke()) {
			apoteke.add(a);
		}

		for (Apoteka a : apoteke) {
			apotekeDTO.add(new ApotekaDTO(a));
		}

		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/apotekaFarmaceut") // apoteka u kojoj farmaceut radi
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<ApotekaDTO> findAllF(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Farmaceut f = (Farmaceut) k;

		Apoteka apoteka = f.getApoteka();

		ApotekaDTO apotekaDTO = null;

		apotekaDTO = new ApotekaDTO(apoteka);

		return new ResponseEntity<ApotekaDTO>(apotekaDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{apotekaId}")
	public void deleteApoteka(@PathVariable long apotekaId) {

		apotekaService.delete(apotekaId);

	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody Apoteka apoteka) {

		Apoteka apoteka1 = new Apoteka();
		apoteka1.setNaziv(apoteka.getNaziv());
		apotekaService.save(apoteka1);

	}

	// za profil apoteke
	@GetMapping(value = "/apoteka/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<ApotekaDTO> detaljiApoteke(@PathVariable long id) {
		Apoteka apoteka = apotekaService.findById(id);
		double ocena = 0;

		double brojOcena = 0;
		if (!apoteka.getOceneApoteke().isEmpty()) {
			for (OcenaApoteka oa : apoteka.getOceneApoteke()) {

				if (oa != null) {
					ocena += oa.getVrednost();
					brojOcena++;
				}
			}
			ocena = ocena / brojOcena;
		}
System.out.println(apoteka.getLat());
		ApotekaDTO apotekaDTO = new ApotekaDTO(apoteka.getId(),apoteka.getNaziv(), apoteka.getGrad(), ocena, apoteka.getLat(), apoteka.getLng());
		return new ResponseEntity<ApotekaDTO>(apotekaDTO, HttpStatus.OK);

	}

	// za profil apoteke
	@GetMapping(value = "/lekari/apoteka/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> lekariApoteke(@PathVariable long id) {
		Apoteka apoteka = apotekaService.findById(id);
		List<LekarDTO> lekari = new ArrayList<LekarDTO>();
		for (Dermatolog d : apoteka.getDermatolozi()) {
			if (d != null) {
				double ocena = 0;
				double brojOcena = 0;
				if (!d.getOceneDermatologa().isEmpty()) {

					for (OcenaDermatolog od : d.getOceneDermatologa()) {
						ocena += od.getVrednost();
						brojOcena++;
					}
					ocena = ocena / brojOcena;
				}
				lekari.add(new LekarDTO(d.getId(), d.getIme() + " " + d.getPrezime(), "dermatolog", ocena));
			}
		}
		for (Farmaceut f : apoteka.getFarmaceuti()) {
			if (f != null) {
				double ocena = 0;
				double brojOcena = 0;
				if (!f.getOceneFarmaceuta().isEmpty()) {

					for (OcenaFarmaceut od : f.getOceneFarmaceuta()) {
						ocena += od.getVrednost();
						brojOcena++;
					}
					ocena = ocena / brojOcena;
				}
				lekari.add(new LekarDTO(f.getId(), f.getIme() + " " + f.getPrezime(), "farmaceut", ocena));
			}
		}

		return new ResponseEntity<List<LekarDTO>>(lekari, HttpStatus.OK);
	}

	@PostMapping(value = "/slobodneApoteke")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<Set<ApotekaDTO>> apotekeKojeImajuSlobodneTermine(@RequestBody String datumIVreme) {
		String datum = datumIVreme.split("T")[0];
		String vremeString = datumIVreme.split("T")[1];
		System.out.println(datum);
		System.out.println(vremeString);

		Set<ApotekaDTO> apotekeSaSlobodnimFarmaceutom = new HashSet<ApotekaDTO>();

		DateTime vreme = new DateTime(Integer.parseInt(datum.split("-")[0]), Integer.parseInt(datum.split("-")[1]),
				Integer.parseInt(datum.split("-")[2]), Integer.parseInt(vremeString.split("%3A")[0]),
				Integer.parseInt(vremeString.split("%3A")[1].substring(0, vremeString.split("%3A")[1].length() - 1)));
		Interval noviPregled = new Interval(vreme, new Period(1800000)); // pola sata za pregled
		for (Apoteka apoteka : apotekaService.findAll()) {
			int slobodanFarmaceut = 0;
			for (Farmaceut farmaceut : apoteka.getFarmaceuti()) {
				int slobodanPregled = 0;
				int slobodanUTokuRadnogVremena = 0;
				for (Pregled pregled : farmaceut.getPregledi()) {
					Interval interval = new Interval(pregled.getInterval());
					if (pregled != null && pregled.getApoteka().equals(apoteka)
							&& (interval.contains(noviPregled) || interval.overlaps(noviPregled))) {
						slobodanPregled++;
					}
				}
				for (RadnoVreme rv : farmaceut.getRadnoVreme()) {
					Interval interval = new Interval(rv.getInterval());
					if (rv != null && rv.getApoteka().equals(apoteka) && interval.contains(noviPregled)) {
						slobodanUTokuRadnogVremena++;

					}
				}
				System.out.println("slobodan pregled: " + slobodanPregled);
				System.out.println("slobodanUTokuRadnogVremena: " + slobodanUTokuRadnogVremena);

				if (slobodanPregled == 0 && slobodanUTokuRadnogVremena != 0) {
					slobodanFarmaceut++;
				}
			}
			double ocena=0;
			double brojOcena=0;
			if (!apoteka.getOceneApoteke().isEmpty()) {
				for (OcenaApoteka oa : apoteka.getOceneApoteke()) {

					if (oa != null) {
						ocena += oa.getVrednost();
						brojOcena++;
					}
				}
				ocena = ocena / brojOcena;
			}
			
			if (slobodanFarmaceut > 0) {
				apotekeSaSlobodnimFarmaceutom.add(new ApotekaDTO(apoteka,ocena));
			}
		}

		return new ResponseEntity<Set<ApotekaDTO>>(apotekeSaSlobodnimFarmaceutom, HttpStatus.OK);
	}

	// za apoteke koje imaju na stanju lek koji zeli da se rezervise
	@GetMapping(value = "/rezervacijaLeka/{nazivLeka}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<ApotekaDTO>> apotekeSaLekovimaNaStanju(@PathVariable String nazivLeka) {

		List<ApotekaDTO> apotekeSaLekovimaNaStanju = new ArrayList<ApotekaDTO>();
		SifrarnikLekova sl = sifrarnikService.findByNaziv(nazivLeka);

		for (Lek lek : lekService.findBySifrarnikLekova(sl.getId())) {
			if (lek.getKolicina() > 0 && lek.getApoteka()!=null) {
				double ocena = 0;
				double brojOcena = 0;
				for (OcenaApoteka oa : lek.getApoteka().getOceneApoteke()) {
					ocena += oa.getVrednost();
					brojOcena++;
				}
				ocena = ocena / brojOcena;
				apotekeSaLekovimaNaStanju.add(new ApotekaDTO(lek.getApoteka(), ocena));
			}
		}

		return new ResponseEntity<List<ApotekaDTO>>(apotekeSaLekovimaNaStanju, HttpStatus.OK);
	}

	// za apoteke koje pacijent moze da oceni
	@GetMapping(value = "/apotekeZaOcenjivanje")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<ApotekaDTO>> apotekeZaOcenjivanje(HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		List<ApotekaDTO> apoteke = new ArrayList<ApotekaDTO>();

		for (Pregled pregled : pregledService.findByPacijentId(p.getId())) {
			Apoteka a = pregled.getApoteka();
			if (pregled.getStatus().equals("odradjen") && !apoteke.contains(new ApotekaDTO(a))) {
				double ocena = 0;
				double brojOcena = 0;
				for (OcenaApoteka oa : a.getOceneApoteke()) {
					ocena += oa.getVrednost();
					brojOcena++;
				}
				ocena = ocena / brojOcena;
				apoteke.add(new ApotekaDTO(a, ocena));
			}
		}
		for (Rezervacija rezervacija : rezervacijaService.findByPacijentId(p.getId())) {
			Apoteka a = rezervacija.getLek().getApoteka();
			if (rezervacija.getStatus().equals("preuzeto") && !apoteke.contains(new ApotekaDTO(a))) {
				double ocena = 0;
				double brojOcena = 0;
				for (OcenaApoteka oa : a.getOceneApoteke()) {
					ocena += oa.getVrednost();
					brojOcena++;
				}
				ocena = ocena / brojOcena;
				apoteke.add(new ApotekaDTO(a, ocena));
			}
		}

		return new ResponseEntity<List<ApotekaDTO>>(apoteke, HttpStatus.OK);
	}

	@GetMapping(value = "/nazivi")
	@PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('PACIJENT')")
	public ResponseEntity<List<String>> sviGradovi() {
		List<String> nazivi = new ArrayList<String>();
		for (Apoteka a : apotekaService.findAll()) {
			if (!nazivi.contains(a.getNaziv())) {
				nazivi.add(a.getNaziv());
				System.out.println(a.getNaziv());
			}
		}
		System.out.println("usao");

		return new ResponseEntity<List<String>>(nazivi, HttpStatus.OK);
	}
	
	@GetMapping(value = "/podaciApoteke")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<ApotekaDTO> podaciApoteke(HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);

		Apoteka apoteka = admin.getApoteka();

		double ocena = 0;
		double brOcena = 0;
		if (!apoteka.getOceneApoteke().isEmpty()) {
			for (OcenaApoteka oa : apoteka.getOceneApoteke()) {
				ocena += oa.getVrednost();
				brOcena++;
			}
			ocena = ocena / brOcena;
		}

		ApotekaDTO apotekaDTO = new ApotekaDTO(apoteka.getId(), apoteka.getNaziv(), apoteka.getGrad(),
				apoteka.getUlica(), apoteka.getDrzava(), ocena, apoteka.getLat(), apoteka.getLng());
		
		return new ResponseEntity<ApotekaDTO>(apotekaDTO, HttpStatus.OK);
	}
	
	@PutMapping(value="/promeniPodatke")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<ApotekaDTO> promeniPodatke(@RequestBody ApotekaDTO apotekaDTO, HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);

		Apoteka apoteka = admin.getApoteka();
		apoteka.setNaziv(apotekaDTO.getNaziv());
		apoteka.setDrzava(apotekaDTO.getDrzava());
		apoteka.setGrad(apotekaDTO.getGrad());
		apoteka.setUlica(apotekaDTO.getUlica());
		apoteka.setLat(apotekaDTO.getLat());
		apoteka.setLng(apotekaDTO.getLng());

		apotekaService.save(apoteka);

		return new ResponseEntity<ApotekaDTO>(apotekaDTO, HttpStatus.OK);


	}


}
