package tim73.isa_2020.controller;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable;
import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.dto.PregledZaPacijentaDTO;
import tim73.isa_2020.dto.RezervacijaPregledaKodFarmaceutaDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.model.TipPregleda;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.TipPregledaService;

@RestController
@RequestMapping(value = "/pregledi")
@CrossOrigin(origins = "http://localhost:3000")
public class PregledController {
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private FarmaceutService farmaceutService;
	
	@Autowired
	private PacijentService pacijentService;
	
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
	private TipPregledaService tipService;
	
	/*
	@GetMapping(value = "/addPregled")
	ResponseEntity<String> add(){
		

		
		DateTime start=new DateTime(2021, 1, 7, 8, 00, 00);
		DateTime stop=new DateTime( 2021, 1, 7, 15, 00, 00);
		Interval interval = new Interval("2021-01-07T08:00:00.000+01:00/2021-01-07T15:00:00.000+01:00" );
		System.out.println(interval.toString());
		Pregled pregled = new Pregled(start, stop, interval.toString(), "default", null, null);
		
		
		DateTime start=new DateTime(2020, 12, 20, 14, 00, 00);
		DateTime stop=new DateTime( 2020, 12, 20, 15, 00, 00);
		Interval interval = new Interval( start, stop );
		Pregled pregled = new Pregled(start, stop, interval, "rezervisan", null, null);
	 	
		
		DateTime start2=new DateTime(2020, 11, 20, 14, 00, 00);
		DateTime stop2=new DateTime( 2020, 11, 20, 16, 00, 00);
		Interval interval2 = new org.joda.time.Interval( start2, stop2 );
		Pregled pregled2 = new Pregled(start2, stop2, interval2, "default", null, null);
		
		DateTime start1=new DateTime(2020, 8, 19, 14, 00, 00);
		DateTime stop1=new DateTime( 2020, 8, 19, 15, 00, 00);
		Interval interval1 = new org.joda.time.Interval( start1, stop1 );
		Pregled pregled1 = new Pregled(start1, stop1, interval1, "default", null, null);
		
		DateTime start1=new DateTime(2020, 12, 28, 23, 00, 00);
		DateTime stop1=new DateTime( 2020, 12, 28, 23, 15, 00);
		Interval interval1 = new org.joda.time.Interval( start1, stop1 );
		Pregled pregled1 = new Pregled(start1, stop1, interval1, "odradjen", null, null);
		
		
		DateTime start3=new DateTime(2021, 1, 15, 14, 00, 00);
		DateTime stop3=new DateTime( 2021, 1, 15, 15, 00, 00);
		Interval interval3 = new org.joda.time.Interval( start3, stop3 );
		Pregled pregled3 = new Pregled(start3, stop3, interval3, "rezervisan", null, null);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(2);
		Apoteka apoteka3= apotekaService.findById(3);
		Farmaceut farmaceut = farmaceutService.findOne((long)4);
		Pacijent pacijent = pacijentService.findById((long)3);
		Pacijent pacijent2 = pacijentService.findById((long)5);
		
		TipPregleda tip = new TipPregleda("pregled mladeza", 1000.0);
		
		TipPregleda tip1 = new TipPregleda("savetovanje o nuspojavama bromazepama", 1000.0);
		
		pregled.setDermatolog(dermatolog);
		pregled.setApoteka(apoteka);
		pregled.setPacijent(pacijent2);
		pregledService.save(pregled);
		
		pregled3.setDermatolog(dermatolog);
		pregled3.setApoteka(apoteka3);
		pregled3.setPacijent(pacijent);
		pregledService.save(pregled3);
		
		Set<Pregled> pregledi = new HashSet<Pregled>();
		pregledi.add(pregled);
		pregledi.add(pregled3);
		
		
		tipService.save(tip);
		tipService.save(tip1);
		
		
		pregled.setTip(tip);
		pregled3.setTip(tip);
		pregled2.setTip(tip);
		pregled1.setTip(tip1);
		
		pregled2.setDermatolog(dermatolog);
		pregled2.setApoteka(apoteka);
		pregled2.setPacijent(pacijent2);
		pregledService.save(pregled2);
		
		pregled1.setFarmaceut(farmaceut);
		pregled1.setApoteka(apoteka);
		pregled1.setPacijent(pacijent2);
		pregledService.save(pregled1);
		
		return new ResponseEntity<>(pregled.toString(), HttpStatus.OK);
	}
*/	

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> findOne(@PathVariable Long id) {
		
		List<Pregled> pregledi= pregledService.findByApotekaId(id);
		
        List<PregledDTO> preglediDTO= new ArrayList<>();
		
		for(Pregled p: pregledi) {
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getEmail(), p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}	
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/pregled/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity <PregledDTO> findOneById(@PathVariable Long id) {
		
		Pregled pregled = pregledService.findOne(id);
		
		PregledDTO pregledDTO = new PregledDTO(pregled);
		
		return new ResponseEntity <PregledDTO>(pregledDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/svi/{id}") //id apoteke za koju su mu potrebni svi pregledi, jer dermatolog radi u vise apoteka
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> findAll(@PathVariable Long id, HttpServletRequest request) {
		
		Set<Pregled> pregledi = new HashSet<Pregled>();
		
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		
		List<PregledDTO> preglediDTO= new ArrayList<>();
		
		
			Dermatolog d = (Dermatolog) k;
			
			for(Pregled p:d.getPregledi()) {
				if(p.getApoteka().getId().equals(id)) {
			       pregledi.add(p);
				}
			}
			
		for(Pregled p: pregledi) {
			if(p.getPacijent()!=null) {
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getEmail(), p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}else {
			preglediDTO.add(new PregledDTO(p));
		}
		}
	 
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/pacijentPregledi", produces = MediaType.APPLICATION_JSON_VALUE) //svi predstojeci (zakazani, neodradjeni) pregledi nekog pacijenta za kog dermatolog zapocinje pregled
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> findAllForPatient(@RequestParam(value="email") String email,  HttpServletRequest request) { //email pacijenta je jedinstven
		
		Set<Pregled> pregledi = new HashSet<Pregled>();
		System.out.println("email " + email);
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username); //preko tokena nalazimo dermatologa
		
		
		List<PregledDTO> preglediDTO= new ArrayList<>();
		
		
			Dermatolog d = (Dermatolog) k;
			
			Korisnik pacijent1 =  korisnikService.findByEmail(email);
			System.out.println(pacijent1.getEmail() + " drzava ");
			
			Pacijent pacijent = (Pacijent) pacijent1;
			
			for(Pregled p:pacijent.getPregledi()) {
				if(p.getDermatolog().equals(d)) {
					if(p.getStatus().equals("zakazan")) {
			       pregledi.add(p);
					}
				}
			}
			
		for(Pregled p: pregledi) {
			
			preglediDTO.add(new PregledDTO(p, pacijent.getEmail(), pacijent.getIme(), pacijent.getPrezime()));
		
		}
	 
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/svi")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<List<PregledDTO>> findPreglediFarmaceut(HttpServletRequest request) {
		
		Set<Pregled> pregledi = new HashSet<Pregled>();
		
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		
		List<PregledDTO> preglediDTO= new ArrayList<>();
		
		
			Farmaceut f = (Farmaceut) k;
			pregledi = f.getPregledi();
			
		for(Pregled p: pregledi) {
			if(p.getPacijent()!=null) {
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getEmail(), p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}else {
			preglediDTO.add(new PregledDTO(p));
		}
		}
	 
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	
	//metoda kojoj pristupa pacijent da bi video istoriju pregleda kod dermatologa
	
	@GetMapping(value = "/istorijaDermatolozi")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<PregledZaPacijentaDTO>> preglediKodDermatologa(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		List<PregledZaPacijentaDTO> preglediDTO= new ArrayList<PregledZaPacijentaDTO>();
		
		
		Set<Pregled> pregledi=p.getPregledi();
		
		for (Pregled pregled:pregledi) {
			if(pregled.getDermatolog()!=null && pregled.getStatus().equals("odradjen")) {
				/*
				double cena=0;
				for (TipPregleda tip : pregled.getTipovi()) {
					cena += tip.getCena();
				}
				*/
				Interval interval=new Interval(pregled.getInterval());
				System.out.println(interval.getStart().toString("dd/MM/yyyy HH:mm"));
				double trajanje= (interval.getEndMillis()-interval.getStartMillis())/60000; //pretvaranje u minute
				preglediDTO.add(new PregledZaPacijentaDTO(pregled,
						(pregled.getDermatolog().getIme() + " " + pregled.getDermatolog().getPrezime()), interval.getStart().toString("dd/MM/yyyy HH:mm"), pregled.getTip().getCena(), trajanje));
			}
		}
		
		return new ResponseEntity<List<PregledZaPacijentaDTO>>(preglediDTO, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/istorijaFarmaceuti")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<PregledZaPacijentaDTO>> preglediKodFarmaceuta(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		List<PregledZaPacijentaDTO> preglediDTO= new ArrayList<PregledZaPacijentaDTO>();
		
		
		Set<Pregled> pregledi=p.getPregledi();
		
		for (Pregled pregled:pregledi) {
			if(pregled.getFarmaceut()!=null && pregled.getStatus().equals("odradjen")) {
				/*
				double cena=0;
				for (TipPregleda tip : pregled.getTipovi()) {
					cena += tip.getCena();
				}
				*/
				Interval interval=new Interval(pregled.getInterval());
				double trajanje= (interval.getEndMillis()-interval.getStartMillis())/60000; //pretvaranje u minute
				preglediDTO.add(new PregledZaPacijentaDTO(pregled,
						(pregled.getFarmaceut().getIme() + " " + pregled.getFarmaceut().getPrezime()), interval.getStart().toString("dd/MM/yyyy HH:mm"), pregled.getTip().getCena(), trajanje));
			}
		}
		
		return new ResponseEntity<List<PregledZaPacijentaDTO>>(preglediDTO, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/detaljiPregleda/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<PregledZaPacijentaDTO> detaljiPregleda( @PathVariable Long id ) {

		Pregled pregled= pregledService.findOne(id);
		PregledZaPacijentaDTO pregledDTO=null;
		/*
		double cena=0;
		for (TipPregleda tip : pregled.getTipovi()) {
			cena += tip.getCena();
		}
		*/
		Interval interval = new Interval(pregled.getInterval());
		double trajanje = (interval.getEndMillis() - interval.getStartMillis()) / 60000;

		if (pregled.getDermatolog() != null) {

			pregledDTO = new PregledZaPacijentaDTO(pregled,
					(pregled.getDermatolog().getIme() + " " + pregled.getDermatolog().getPrezime()),
					interval.getStart().toString("dd/MM/yyyy HH:mm"), pregled.getTip().getCena(), trajanje);
		} else {
			pregledDTO = new PregledZaPacijentaDTO(pregled,
					(pregled.getFarmaceut().getIme() + " " + pregled.getFarmaceut().getPrezime()),
					interval.getStart().toString("dd/MM/yyyy HH:mm"), pregled.getTip().getCena(), trajanje);

		}
		return new ResponseEntity<PregledZaPacijentaDTO>(pregledDTO, HttpStatus.OK);
		
	}

	@GetMapping(value = "/zakazaniPregledi")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<PregledZaPacijentaDTO>> zakazaniPregledi(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		List<PregledZaPacijentaDTO> preglediDTO= new ArrayList<PregledZaPacijentaDTO>();
		
		
		Set<Pregled> pregledi=p.getPregledi();
		
		for (Pregled pregled:pregledi) {
			if(pregled.getStatus().equals("rezervisan")) {
				double cena=2000;
				Interval interval = new Interval(pregled.getInterval());

				double trajanje = interval.getEndMillis() - interval.getStartMillis()/ 60000; // pretvaranje u minute
				if (pregled.getFarmaceut() != null) {
					preglediDTO.add(new PregledZaPacijentaDTO(pregled,
							(pregled.getFarmaceut().getIme() + " " + pregled.getFarmaceut().getPrezime()),
							interval.getStart().toString("dd/MM/yyyy HH:mm"), cena, trajanje));
				}
				else {
					preglediDTO.add(new PregledZaPacijentaDTO(pregled,
							(pregled.getDermatolog().getIme() + " " + pregled.getDermatolog().getPrezime()),
							interval.getStart().toString("dd/MM/yyyy HH:mm"), cena, trajanje));
				}
			}
		}
		
		return new ResponseEntity<List<PregledZaPacijentaDTO>>(preglediDTO, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/otkaziPregled/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> otkaziPregled(@PathVariable Long id) {

		Pregled pregled = pregledService.findOne(id);
		Interval interval = new Interval(pregled.getInterval());

		if((interval.getStartMillis()-System.currentTimeMillis())/3600000>=24) {
			pregled.setStatus("default");
			pregledService.save(pregled);
			return new ResponseEntity<String>("Pregled je uspesno otkazan", HttpStatus.OK);

		}else {
			return new ResponseEntity<>("Pregled mozete otkazati najmanje 24h pre zakazanog vremena", HttpStatus.BAD_REQUEST);

		}
	}
	
	//prikaz unapreg kreiranih pregleda kod dermatologa
	@GetMapping(value = "/pregledi/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<PregledZaPacijentaDTO>> preglediKodDermatologa(@PathVariable long id){
		List<PregledZaPacijentaDTO> preglediDTO= new ArrayList<PregledZaPacijentaDTO>();
		for(Pregled p:pregledService.findByApotekaIdAndStatus(id, "default")) {
			Interval interval = new Interval(p.getInterval());

			if(p.getDermatolog()!=null) {
				double trajanje= (interval.getEndMillis()-interval.getStartMillis())/60000; //pretvaranje u minute

				preglediDTO.add(new PregledZaPacijentaDTO(p, p.getDermatolog().getIme()+" "+p.getDermatolog().getPrezime(), interval.getStart().toString("dd/MM/yyyy HH:mm"), 2000, trajanje));
			}
		}
		return new ResponseEntity<List<PregledZaPacijentaDTO>>(preglediDTO,HttpStatus.OK);
		
	}
	//prikaz unapreg kreiranih pregleda kod dermatologa pri zakazivanju novog pregleda pacijentu
		@GetMapping(value = "/unapredDefinisaniPregledi")
		@PreAuthorize("hasRole('DERMATOLOG')")
		public ResponseEntity<List<PregledDTO>> unapredDefinisaniPreglediKodDermatologa(@RequestParam("email") String email,@RequestParam("pregled") long pregled, HttpServletRequest request){
			List<PregledDTO> preglediDTO= new ArrayList<PregledDTO>();
			Pregled pregleD = pregledService.findOne(pregled);
			for(Pregled p:pregledService.findByApotekaIdAndStatus(pregleD.getApoteka().getId(), "default")) {
				
				if(p.getDermatolog()!=null) {
					preglediDTO.add(new PregledDTO(p));
				}
			}
			return new ResponseEntity<List<PregledDTO>>(preglediDTO,HttpStatus.OK);
			
		}
	
		
	@PutMapping(value="/rezervisi/{id}", produces = "application/json")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> rezervacija(@PathVariable long id, HttpServletRequest request) throws MailException, InterruptedException {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		
		
		if(p.getPenal()>=3) {
			return ResponseEntity
		            .badRequest().body("Imate 3 ili vise penala i ova funkcija Vam nije dostupna!");
		}
		Pregled pregled = pregledService.findOne(id);
		Interval interval = new Interval(pregled.getInterval());

		if (pregled.getStatus().equals("default")) {
			pregled.setStatus("rezervisan");
			pregled.setPacijent(p);
			pregledService.save(pregled);
			mailService.sendSimpleMessage(p.getEmail(), "REZERVACIJA", "Uspesno ste rezervisali pregled kod dermatologa za "
					+ interval.getStart().toString("dd/MM/yyyy HH:mm"));
		}
		
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	

	@PostMapping(value="/noviPregled")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> noviPregled(@RequestBody RezervacijaPregledaKodFarmaceutaDTO rezervacija, HttpServletRequest request) throws MailException, InterruptedException   {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.korisnikDetails.loadUserByUsername(username);
		
		
		if(p.getPenal()>=3) {
			return ResponseEntity
		            .badRequest().body("Imate 3 ili vise penala i ova funkcija Vam nije dostupna!");
		}
		
		String datum = rezervacija.getDatum().split("T")[0];
		String vremeString = rezervacija.getDatum().split("T")[1];
		System.out.println(datum);
		System.out.println(vremeString);
		
		
		DateTime vremePregleda = new DateTime(Integer.parseInt(datum.split("-")[0]), Integer.parseInt(datum.split("-")[1]),
				Integer.parseInt(datum.split("-")[2]), Integer.parseInt(vremeString.split(":")[0]), Integer.parseInt(vremeString.split(":")[1]));
		Interval noviPregled= new Interval(vremePregleda, new Period(1800000)); // pola sata za pregled
		

		for (Pregled postojeciPregledi : pregledService.findByFarmaceutId(rezervacija.getIdFarmaceuta())) {
			Interval interval = new Interval(postojeciPregledi.getInterval());

			if (interval.contains(noviPregled)) {
				return new ResponseEntity<String>("postoji vec", HttpStatus.CONFLICT);
			}
		}
		
		Pregled pregled= new Pregled(null, null, noviPregled.toString(), "rezervisan", null, null);
		
		pregled.setApoteka(apotekaService.findById(rezervacija.getIdApoteke()));
		pregled.setFarmaceut(farmaceutService.findOne(rezervacija.getIdFarmaceuta()));
		pregled.setPacijent(p);
		
		pregledService.save(pregled);
		
		mailService.sendSimpleMessage(p.getEmail(), "REZERVACIJA", "Uspesno ste rezervisali pregled kod farmaceuta za "+datum+" u "+vremeString+".");
		return new ResponseEntity<String>("ok", HttpStatus.OK);
 
	}
	
	@GetMapping(value = "/pregledi")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> preglediPacijent(@RequestParam("email") String email, HttpServletRequest request){
      List<PregledDTO> preglediDTO= new ArrayList<PregledDTO>();
		Pacijent p = (Pacijent) korisnikService.findByEmail(email);

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		Dermatolog d = (Dermatolog) k;
		Set<Pregled> pregledi=p.getPregledi();
		
		for (Pregled pregled:pregledi) {
			if(pregled.getStatus().equals("rezervisan")) {
				
				if(pregled.getDermatolog()!=null&&pregled.getDermatolog().equals(d)) {
				
							preglediDTO.add(new PregledDTO(pregled));
				}
				}
				
			}
	
		return new ResponseEntity<List<PregledDTO>>(preglediDTO,HttpStatus.OK);
		
	}
	@GetMapping(value = "/preglediKodFarmaceuta") 
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<List<PregledDTO>> preglediPacijentKodFarmaceuta(@RequestParam("email") String email, HttpServletRequest request){
      List<PregledDTO> preglediDTO= new ArrayList<PregledDTO>();
		Pacijent p = (Pacijent) korisnikService.findByEmail(email);

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		Farmaceut f = (Farmaceut) k;
		Set<Pregled> pregledi=p.getPregledi();
		
		for (Pregled pregled:pregledi) {
			if(pregled.getStatus().equals("rezervisan")) {
				
				if(pregled.getFarmaceut()!=null&&pregled.getFarmaceut().equals(f)) {
				
							preglediDTO.add(new PregledDTO(pregled));
				}
				}
				
			}
	
		return new ResponseEntity<List<PregledDTO>>(preglediDTO,HttpStatus.OK);
		
	}
	static class PregledKraj{
		public String informacije;
		public Long pregledID;
		public String terapija;
	}
	
	@PostMapping(value = "/zavrsiPregled")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<PregledDTO> zavrsiPregled(@RequestBody PregledKraj p){
     
		Pregled pregled = pregledService.findOne(p.pregledID);
		
		pregled.setDijagnoza(p.informacije);
		
		pregled.setTerapija(p.terapija);
		
		pregled.setStatus("odradjen");
		
		pregledService.save(pregled);		
			
		PregledDTO dto = new PregledDTO(pregled);
		return new ResponseEntity<PregledDTO>(dto,HttpStatus.OK);
		

	}
	static class ZakaziPregled{//email pacijenta i id unapred definisanog pregleda koji se zakazuje
		public String email;
		public long id;
	}
	@PostMapping(value = "/zakazi")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<PregledDTO> zakaziPregled(@RequestBody ZakaziPregled p){
     
		Pregled pregled = pregledService.findOne(p.id);
		
		Korisnik k = korisnikService.findByEmail(p.email);
		
		Pacijent pacijent = (Pacijent) k;
		
		pregled.setStatus("rezervisan");
		
		pregled.setPacijent(pacijent);
		
		pregledService.save(pregled);		
			
		PregledDTO dto = new PregledDTO(pregled);
		return new ResponseEntity<PregledDTO>(dto,HttpStatus.OK);
		

	}
    static class NoviPregled{
    	public String email; //mejl pacijenta kom se zakazuje novi pregled
    	public long id; //id pregleda da bi se doslo do apoteke u kojoj se vrsi pregled 
    	public String datumStart; //pocetak pregleda
    }
	@PostMapping(value = "/zakaziNovi")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<PregledDTO> zakaziNoviPregled(@RequestBody NoviPregled p, HttpServletRequest request) throws MailException, InterruptedException{
     
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		Pregled trenutni = pregledService.findOne(p.id);
		
		Apoteka a = trenutni.getApoteka();
		
		Dermatolog d = (Dermatolog) k;
		
		Korisnik korisnik = korisnikService.findByEmail(p.email);
		
		Pacijent pacijent = (Pacijent) korisnik;
		
		boolean flag = false;
		
		Interval interval = new Interval(p.datumStart + ":00.000+01:00" + "/" + p.datumStart + ":00.000+01:00");
		DateTime end = interval.getEnd();
		DateTime endNovi = end.plusMinutes(30);
		
		Interval interval2 = new Interval(p.datumStart + "/" + endNovi);
		
        for(Pregled p1: pacijent.getPregledi()) {
            
        	Interval i = new Interval(p1.getInterval());
        	
		   if(i.overlaps(interval2)){
			   System.out.println("preklapa se kod pacijenta");
			   flag=true;
			   break;
			   
		   }
		   }
        for(Pregled p2: d.getPregledi()) {
        	
        	Interval i = new Interval(p2.getInterval());
        	if(i.overlaps(interval2)){
        		System.out.println("preklapa se kod lekara");
        		flag = true;
        		break;
        	}
        	
        }
        boolean flag2 = false;
        for(RadnoVreme radnoVreme: d.getRadnoVreme()) {
        	Interval i = new Interval(radnoVreme.getInterval());
        	if(i.overlaps(interval2)){
        	flag2 = true;
        	break;
        	}
        }
        PregledDTO dto = null;
			if(!flag&&flag2) {
		
		Pregled noviPregled = new Pregled(interval2.toString() , "rezervisan", "", "");
		
		noviPregled.setPacijent(pacijent);
		
		
		noviPregled.setTip(trenutni.getTip());
		
		noviPregled.setApoteka(a);

		noviPregled.setDermatolog(d);
		
		pregledService.save(noviPregled);		
		
			
	     dto = new PregledDTO(noviPregled);
	     
	     //POSLATI MEJL PACIJENTU
	    /* mailService.sendSimpleMessage(pacijent.getEmail(), "ZAKAZIVANJE PREGLEDA", "Zakazan Vam je pregled kod dermatologa u "
					+ interval.getStart().toString("dd/MM/yyyy HH:mm"));*/
			
			}
			if(!flag2) {
				System.out.println("nije u radnom vremenu");
			}
			
		return new ResponseEntity<PregledDTO>(dto,HttpStatus.OK);
		

	}
	@PostMapping(value = "/zakaziNoviFarmaceut")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<PregledDTO> zakaziNoviKodFarmaceuta(@RequestBody NoviPregled p, HttpServletRequest request){
     
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		Pregled trenutni = pregledService.findOne(p.id);
		
		Apoteka a = trenutni.getApoteka();
		
		Farmaceut f = (Farmaceut) k;
		
		Korisnik korisnik = korisnikService.findByEmail(p.email);
		
		Pacijent pacijent = (Pacijent) korisnik;
		
		boolean flag = false;
		
		Interval interval = new Interval(p.datumStart + ":00.000+01:00" + "/" + p.datumStart + ":00.000+01:00");
		DateTime end = interval.getEnd();
		DateTime endNovi = end.plusMinutes(30);
		
		Interval interval2 = new Interval(p.datumStart + "/" + endNovi);
		
        for(Pregled p1: pacijent.getPregledi()) {
            
        	Interval i = new Interval(p1.getInterval());
        	
		   if(i.overlaps(interval2)){
			   System.out.println("preklapa se kod pacijenta");
			   flag=true;
			   break;
			   
		   }
		   }
        for(Pregled p2: f.getPregledi()) {
        	
        	Interval i = new Interval(p2.getInterval());
        	if(i.overlaps(interval2)){
        		System.out.println("preklapa se kod lekara");
        		flag = true;
        		break;
        	}
        	
        }
        boolean flag2 = false;
        for(RadnoVreme radnoVreme: f.getRadnoVreme()) {
        	Interval i = new Interval(radnoVreme.getInterval());
        	if(i.overlaps(interval2)){
        	flag2 = true;
        	break;
        	}
        }
        PregledDTO dto = null;
			if(!flag&&flag2) {
		
		Pregled noviPregled = new Pregled(interval2.toString() , "rezervisan", "", "");
		
		noviPregled.setPacijent(pacijent);
		
		
		noviPregled.setTip(trenutni.getTip());
		
		noviPregled.setApoteka(a);

		noviPregled.setFarmaceut(f);
		
		pregledService.save(noviPregled);		
		
			
	     dto = new PregledDTO(noviPregled);
	     
	     //POSLATI MEJL PACIJENTU
	    /* mailService.sendSimpleMessage(pacijent.getEmail(), "ZAKAZIVANJE PREGLEDA", "Zakazan Vam je pregled kod farmaceuta u "
					+ interval.getStart().toString("dd/MM/yyyy HH:mm"));*/
			}
			if(!flag2) {
				System.out.println("nije u radnom vremenu");
			}
			
		return new ResponseEntity<PregledDTO>(dto,HttpStatus.OK);
		

	}
}
