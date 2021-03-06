package tim73.isa_2020.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.RezervacijaDTO;
import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.LoyaltyProgram;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.model.StavkeAkcijePromocije;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.LoyaltyProgramService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RezervacijaService;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/rezervacije")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikServiceImpl korisnikDetails;
	
	@Autowired
	private EmailService mailService;
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private SifrarnikLekovaService sifrarnikSevice;

	@Autowired
	private PregledService pregledService;

	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@GetMapping(value = "/setTime/{id}")
	ResponseEntity <RezervacijaDTO>  setTime(@PathVariable Long id) {
		
		Rezervacija rezervacija = rezervacijaService.findOne(id);
		
		rezervacija.setDatumPreuzimanja("2021-01-08T14:00:00.000+01:00");
		//System.out.println(rezervacija.getDatumPreuzimanja().toString());
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
		rezervacijaService.save(rezervacija);
		
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	ResponseEntity <RezervacijaDTO> findOne(@PathVariable Long id) {
		
		Rezervacija rezervacija = rezervacijaService.findOne(id);
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
		
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/search/{brojRezervacije}")
	@PreAuthorize("hasRole('FARMACEUT')")
	ResponseEntity <RezervacijaDTO> zaIzdavanje(@PathVariable Long brojRezervacije, HttpServletRequest request) throws ParseException {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		Rezervacija rezervacija = rezervacijaService.findOne(brojRezervacije);
		
		Farmaceut f = (Farmaceut) k;
		
		RezervacijaDTO rezervacijaDTO = null;
		
		if(rezervacija!=null) {
			if(f.getApoteka().equals(rezervacija.getApoteka())) {
				if(rezervacija.getStatus().equals("izdavanje")) {
					DateTime datumPreuzimanja= new DateTime (rezervacija.getDatumPreuzimanja());
				System.out.println(datumPreuzimanja.getMillis());
				System.out.println(System.currentTimeMillis());
				System.out.println((datumPreuzimanja.getMillis()-System.currentTimeMillis())/3600000);
				if((datumPreuzimanja.getMillis()-System.currentTimeMillis())/3600000>=24){
				rezervacijaDTO = new RezervacijaDTO(rezervacija);
				}else {
					
					System.out.println("nemaa");
				}
				}
		}else {
			
			System.out.println("nemaa");
		}
		}
				
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}
	static class Body {
		public Long id;
		
	}
	@RequestMapping(value = "/izdavanje", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('FARMACEUT')")
	ResponseEntity <RezervacijaDTO> preuzimanjeLeka(@RequestBody Body body, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		Rezervacija rezervacija = rezervacijaService.findOne(body.id);
		if(rezervacija.getStatus().equals("izdavanje")) {
		//	if(rezervacija.getLek().getKolicina()>=kolicina///)
		rezervacija.getLek().setKolicina(rezervacija.getLek().getKolicina()-1);
		}
		rezervacija.setStatus("preuzeto");
		// dodavanje poena za loyalty program kada se preuzme lek
		Pacijent p=rezervacija.getPacijent();
		p.setPoeni(p.getPoeni()+rezervacija.getLek().getSifrarnikLekova().getPoeni());
		for(LoyaltyProgram lp:loyaltyProgramService.findByOrderByPragPoenaDesc()) {
			if(lp.getPragPoena()<=p.getPoeni()) {
				p.setLoyaltyProgram(lp);
				break;
			}
		}
		korisnikService.save(p);
		
		rezervacijaService.save(rezervacija);
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
		
		Pacijent pacijent = rezervacija.getPacijent();
		
		
		
		mailService.sendSimpleMessage(pacijent.getEmail(), "Potvrda", "Uspesno ste pruzeli lek: "
				+ rezervacija.getLek().getSifrarnikLekova().getNaziv() + " u apoteci: " + rezervacija.getApoteka().getNaziv());
				
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/pacijentRezervacija/{idPacijent}")
	ResponseEntity <List<RezervacijaDTO>> findOne1(@PathVariable Long idPacijent) {
		
		List<Rezervacija> rezervacije = rezervacijaService.findByPacijentId(idPacijent);
		

		
		List<RezervacijaDTO> rezervacijeDTO= new ArrayList<>();
		
		for(Rezervacija r: rezervacije) {
			rezervacijeDTO.add(new RezervacijaDTO(r));
		}
		
		return new ResponseEntity<List<RezervacijaDTO>>(rezervacijeDTO, HttpStatus.OK);
	}

	
	static class NovaRezervacija{
		public String nazivLeka;
		public Long apoteka;
		public String vreme;
	}
	
	//pacijent bira lek i vreme do kog ce preuzeti lek
	@PostMapping(value = "/novaRezervacija")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> novaRezervacija(@RequestBody NovaRezervacija novaRezervacija, HttpServletRequest request)
			throws MailException, InterruptedException {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);

		if(p.getPenal()>=3) {
			return ResponseEntity
		            .badRequest().body("Imate 3 ili vise penala i ova funkcija Vam nije dostupna!");
		}
		
		SifrarnikLekova sl = sifrarnikSevice.findByNaziv(novaRezervacija.nazivLeka);

		Lek lek = lekService.findBySifrarnikLekovaIdAndApotekaId(sl.getId(), novaRezervacija.apoteka);

		Rezervacija rezervacija = new Rezervacija(novaRezervacija.vreme + ":00.000+01:00", "izdavanje", lek, p, lek.getApoteka());
		rezervacijaService.save(rezervacija);
		mailService.sendSimpleMessage(p.getEmail(), "REZERVACIJA LEKA", "Uspesno ste rezervisali lek: "
				+ novaRezervacija.nazivLeka + ". Vas jedinstveni broj rezervacije je: " + rezervacija.getId() + ".");
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@GetMapping(value = "/pacijentoveRezervacije")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<RezervacijaDTO>> pacijentoveRezervacije(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		List<RezervacijaDTO> rezervacije=new ArrayList<RezervacijaDTO>();
		
		
		for(Rezervacija r:rezervacijaService.findByPacijentId(p.getId())) {
		    DateTime dt = new DateTime(r.getDatumPreuzimanja());

			double cena=0;
			Lek lek=r.getLek();
			for(Cenovnik cenovnik:r.getApoteka().getCenovnici()) {
				Interval interval=new Interval(cenovnik.getInterval());
				if (dt.isAfter(interval.getStart())&& dt.isBefore(interval.getEnd())) {
					for(CenovnikStavka cs:cenovnik.getStavkeCenovnika()) {
						if(cs.getLek().equals(lek)) {
							cena=cs.getCena();
						}
					}
				}
			}

			for(AkcijaPromocija ap:r.getApoteka().getAkcijePromocije()) {
				Interval interval=new Interval(ap.getVremeVazenja());

				if (dt.isAfter(interval.getStart())&& dt.isBefore(interval.getEnd())) {
					for(StavkeAkcijePromocije aps:ap.getStavke()) {
						if(aps.getLek().equals(lek)) {
							cena=cena*((100-ap.getProcenatAkcije())/100);
						}
					}
				}
			}
			
			//dodatan popust za loyalty program
			cena=cena*((100-p.getLoyaltyProgram().getPopust())/100);
				
			
			rezervacije.add(new RezervacijaDTO(r,cena));
		}
		
		
		
		
		return new ResponseEntity<List<RezervacijaDTO>>(rezervacije, HttpStatus.OK);
		
	}
	
	
	static class RezervacijaLeka {
		public String naziv; //naziv leka
		public String email; //email pacijenta
		public String datumPreuzimanja;
		public Long id; //id pregleda
	}
	@PostMapping(value = "/rezervisi")
	ResponseEntity <RezervacijaDTO> rezervacijaLeka(@RequestBody RezervacijaLeka rezervacija) {
		
		Apoteka apoteka = pregledService.findOne(rezervacija.id).getApoteka();
		Set<Lek> lekApoteka = apoteka.getLekovi();
		
		Korisnik pacijent = korisnikService.findByEmail(rezervacija.email);
		Pacijent p = (Pacijent) pacijent;
		
		 Lek lek= null;
		
		for(Lek l: lekApoteka) {
			if(l.getSifrarnikLekova().getNaziv().equals(rezervacija.naziv)) {
				lek = l;
			}
		}
		
		
		Rezervacija rezervisi = new Rezervacija(rezervacija.datumPreuzimanja + ":00.000+01:00", "izdavanje" , lek, p, lek.getApoteka());
		rezervacijaService.save(rezervisi);
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervisi);
		
			
		
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}
	
	@PutMapping(value ="/otkazivanjeRezervacije/{idRezervacije}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> otkazivanjeRezervacije(@PathVariable Long idRezervacije, HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		Rezervacija rezervacija= rezervacijaService.findOne(idRezervacije);
		//		if((interval.getStartMillis()-System.currentTimeMillis())/3600000>=24) {

		DateTime datumPreuzimanja = new DateTime(rezervacija.getDatumPreuzimanja());
		if((datumPreuzimanja.getMillis()-System.currentTimeMillis())/3600000>=24) {
			System.out.println("otkazivanje");
			rezervacija.setStatus("otkazan");
			rezervacijaService.save(rezervacija);
			return new ResponseEntity<String>("Rezervacija je uspesno otkazana", HttpStatus.OK);
		}
		else {
			return  ResponseEntity.badRequest().body("Rezervaciju mozete otkazati najmanje 24h pre datuma preuzimanja");
		}		
	}




}

