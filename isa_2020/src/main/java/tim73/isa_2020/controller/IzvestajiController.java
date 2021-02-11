package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.LoyaltyProgramDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.LoyaltyProgram;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.model.StavkeAkcijePromocije;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RezervacijaService;

@RestController
@RequestMapping(value = "/izvestaji")
@CrossOrigin(origins = "http://localhost:3000")
public class IzvestajiController {

	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	
	@GetMapping(value="godisnji/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<Integer>> godisnji(@PathVariable int godina,HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
	
		List<Integer> brojPregledaPoMesecima= new ArrayList<Integer>();
		
		
		
		for(int i=1; i<=12; i++) {
			int brPregleda=0;
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==i) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda++;
				}
			}
			brojPregledaPoMesecima.add(brPregleda);
		}
		
		
		
		
		for(int i:brojPregledaPoMesecima) {
			System.out.println(i);
		}
		
		return new ResponseEntity<List<Integer>>(brojPregledaPoMesecima, HttpStatus.OK);
	}
	
	
	@GetMapping(value="kvartalni/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<Integer>> kvartalni(@PathVariable int godina,HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
	
		List<Integer> brojPregledaPoMesecima= new ArrayList<Integer>();
		
		int brPregleda1=0;
		int brPregleda2=0;
		int brPregleda3=0;
		int brPregleda4=0;
		
		for(int i=1; i<=3; i++) {
			
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==i) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda1++;
				}
			}
			
		}
		
		for(int i=4; i<=6; i++) {
			
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==i) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda2++;
				}
			}
			
		}
		
		for(int i=7; i<=9; i++) {
	
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==i) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda3++;
				}
			}
			
		}
		
		
		for(int i=10; i<=12; i++) {
			
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==i) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda4++;
				}
			}
			
		}
		brojPregledaPoMesecima.add(brPregleda1);
		brojPregledaPoMesecima.add(brPregleda2);
		brojPregledaPoMesecima.add(brPregleda3);
		brojPregledaPoMesecima.add(brPregleda4);
		
		for(int i:brojPregledaPoMesecima) {
			System.out.println(i);
		}
		
		return new ResponseEntity<List<Integer>>(brojPregledaPoMesecima, HttpStatus.OK);
		
		
	}
	
	static class Izvestaj {
		public List<String> dani;
		public List<Integer> brPregleda;
	}
	
	@GetMapping(value="mesecni/{mesec}/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<Izvestaj> mesecni (@PathVariable int mesec,@PathVariable int godina ,HttpServletRequest request){
		int dani=0;
		if(mesec==2) {
			dani=28;
		}
		else if(mesec%2==1){//neparni meseci
			dani=31;
		}
		else {
			dani=30;
		}
		
		Izvestaj izvestaj=new Izvestaj();
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
		List<String> daniString= new ArrayList<String>();

		List<Integer> brojPregledaPoMesecima= new ArrayList<Integer>();
		
		for(int d=1;d<=dani;d++) {
			daniString.add(String.valueOf(d));
			int brPregleda=0;
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval interval=new Interval(pregled.getInterval());
				if(interval.getStart().getYear()==godina&&interval.getStart().getMonthOfYear()==mesec&&interval.getStart().getDayOfMonth()==d) {
					System.out.println("godina:" + interval.getStart().getYear() + " mesec: "+interval.getStart().getMonthOfYear());
					brPregleda++;
					
				}
			}
			brojPregledaPoMesecima.add(brPregleda);
		}
		System.out.println(dani);
		izvestaj.brPregleda=brojPregledaPoMesecima;
		izvestaj.dani=daniString;
		return new ResponseEntity<Izvestaj>(izvestaj, HttpStatus.OK);
	}
	
	static class IzvestajPrihodi {
		public List<String> dani;
		public List<Double> cene;
	}
	
	@GetMapping(value="prihodi/{datumOd}/{datumDo}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<IzvestajPrihodi> prihodi (@PathVariable String datumOd,@PathVariable String datumDo ,HttpServletRequest request){
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
		List<String> daniString= new ArrayList<String>();
		List<Double> cenePregleda= new ArrayList<Double>();
		
		Interval interval=new Interval(datumOd+"T00:00:00.000+01:00"+"/"+datumDo+"T00:00:00.000+01:00");
		long start=interval.getStart().getMillis();
		DateTime vreme=interval.getStart();
		do{
			daniString.add(vreme.toString("dd/MM/yyyy"));
			double cena=0;
			double cenaKonacno=0;
			for(Pregled pregled:pregledService.findByApotekaIdAndStatus(apoteka.getId(), "odradjen")) {
				Interval intervalPregled=new Interval(pregled.getInterval());
				if(intervalPregled.getStart().getYear()==vreme.getYear() && intervalPregled.getStart().getDayOfMonth()==vreme.getDayOfMonth() &&
						intervalPregled.getStart().getMonthOfYear()==vreme.getMonthOfYear()) {
					Pacijent p=pregled.getPacijent();
					
					cena+=pregled.getTip().getCena()*((100-p.getLoyaltyProgram().getPopust())/100);
				}
			}
			double cenaRezervacija=0;
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				double cenaRezervacije=0;
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==vreme.getYear() && datum.getDayOfMonth()==vreme.getDayOfMonth() &&
						datum.getMonthOfYear()==vreme.getMonthOfYear()) {
					Lek lek = rezervacija.getLek();
					for (Cenovnik cenovnik : rezervacija.getApoteka().getCenovnici()) {
						Interval intervalCenovnika = new Interval(cenovnik.getInterval());
						if (datum.isAfter(intervalCenovnika.getStart()) && datum.isBefore(intervalCenovnika.getEnd())) {
							for (CenovnikStavka cs : cenovnik.getStavkeCenovnika()) {
								if (cs.getLek().equals(lek)) {
									cenaRezervacije = cs.getCena();
								}
							}
						}
					}

					for (AkcijaPromocija ap : rezervacija.getApoteka().getAkcijePromocije()) {
						Interval intervalAkcije = new Interval(ap.getVremeVazenja());

						if (datum.isAfter(intervalAkcije.getStart()) && datum.isBefore(intervalAkcije.getEnd())) {
							for (StavkeAkcijePromocije aps : ap.getStavke()) {
								if (aps.getLek().equals(lek)) {
									cenaRezervacije = cenaRezervacije * ((100 - ap.getProcenatAkcije()) / 100);
								}
							}
						}
					}
				Pacijent p= rezervacija.getPacijent();
				cenaRezervacije=cenaRezervacije*((100 - p.getLoyaltyProgram().getPopust()) / 100);
				cenaRezervacija+=cenaRezervacije;
				}
				System.out.println(cena);
				System.out.println(cenaRezervacija);
				
				cenaKonacno=cena+cenaRezervacija;
				
			}
			
			cenePregleda.add(cenaKonacno);
			start+=86400000;
			vreme=new DateTime(start);
			
		}while(start<=interval.getEndMillis());
		
		IzvestajPrihodi izvestaj= new IzvestajPrihodi();
		izvestaj.dani=daniString;
		izvestaj.cene=cenePregleda;
		
		
		return new ResponseEntity<IzvestajPrihodi>(izvestaj, HttpStatus.OK);
	}
	
	@GetMapping(value="godisnjiLekovi/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<Integer>> godisnjiLekovi(@PathVariable int godina,HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
	
		List<Integer> brojLekovaPoMesecima= new ArrayList<Integer>();
		
		
		
		for(int i=1; i<=12; i++) {
			int brLekova=0;
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==i) {
					brLekova++;
				}
			}
			brojLekovaPoMesecima.add(brLekova);
		}
		
		
		
		
		for(int i:brojLekovaPoMesecima) {
			System.out.println(i);
		}
		
		return new ResponseEntity<List<Integer>>(brojLekovaPoMesecima, HttpStatus.OK);
	}
	
	
	@GetMapping(value="kvartalniLekovi/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<Integer>> kvartalniLekovi(@PathVariable int godina,HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
	
		List<Integer> brojLekovaPoMesecima= new ArrayList<Integer>();
		
		int brLekova1=0;
		int brLekova2=0;
		int brLekova3=0;
		int brLekova4=0;
		
		for(int i=1; i<=3; i++) {
			
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==i) {
					brLekova1++;
				}
			}
			
		}
		
		for(int i=4; i<=6; i++) {
			
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==i) {
					brLekova2++;
				}
			}
			
		}
		
		for(int i=7; i<=9; i++) {
	
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==i) {
					brLekova3++;
				}
			}
			
		}
		
		
		for(int i=10; i<=12; i++) {
			
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==i) {
					brLekova4++;
				}
			}
			
		}
		brojLekovaPoMesecima.add(brLekova1);
		brojLekovaPoMesecima.add(brLekova2);
		brojLekovaPoMesecima.add(brLekova3);
		brojLekovaPoMesecima.add(brLekova4);
		
		
		return new ResponseEntity<List<Integer>>(brojLekovaPoMesecima, HttpStatus.OK);	
	}
	
	@GetMapping(value="mesecniLekovi/{mesec}/{godina}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<Izvestaj> mesecniLekovi (@PathVariable int mesec,@PathVariable int godina ,HttpServletRequest request){
		int dani=0;
		if(mesec==2) {
			dani=28;
		}
		else if(mesec%2==1){//neparni meseci
			dani=31;
		}
		else {
			dani=30;
		}
		
		Izvestaj izvestaj=new Izvestaj();
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
		List<String> daniString= new ArrayList<String>();

		List<Integer> brojLekovaPoMesecima= new ArrayList<Integer>();
		
		for(int d=1;d<=dani;d++) {
			daniString.add(String.valueOf(d));
			int brLekova=0;
			for(Rezervacija rezervacija:rezervacijaService.findByStatusAndApotekaId("preuzeto", apoteka.getId())) {
				DateTime datum=new DateTime(rezervacija.getDatumPreuzimanja());
				if(datum.getYear()==godina&&datum.getMonthOfYear()==mesec&&datum.getDayOfMonth()==d) {
					brLekova++;
				}
			}
			brojLekovaPoMesecima.add(brLekova);
		}
		
		
		izvestaj.brPregleda=brojLekovaPoMesecima;
		izvestaj.dani=daniString;
		return new ResponseEntity<Izvestaj>(izvestaj, HttpStatus.OK);
	}
	
	
	
}
