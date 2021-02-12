package tim73.isa_2020.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.CenovnikDTO;
import tim73.isa_2020.dto.CenovnikStavkaDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.UpitZaLek;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.CenovnikService;
import tim73.isa_2020.service.CenovnikStavkaService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;


@RestController
@RequestMapping(value = "/cenovnici")
public class CenovnikController {
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private CenovnikStavkaService cenovnikStavkaService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
static class parovi{
		
		public String key; //naziv leka
		public int value; //cena leka
		public String periodVazenjaOd; 
		public String periodVazenjaDo;
		
	}
	@RequestMapping(value = "/dodajCenovnik", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<CenovnikDTO> definisanjeCenovnika(@RequestBody List<parovi> listaLekovaICena, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		 String interval = listaLekovaICena.get(0).periodVazenjaOd + ":00.000+01:00" + "/" + listaLekovaICena.get(0).periodVazenjaDo + ":00.000+01:00";
		 System.out.println(listaLekovaICena.get(0).key + "   " + listaLekovaICena.get(0).value);
		 List<Lek> lekoviApoteke = lekService.findByApotekaId(korisnik.getApoteka().getId());
		 
		 Cenovnik cenovnik = new Cenovnik(interval, korisnik.getApoteka());
		 
		 Set<CenovnikStavka> stavkeLista = new HashSet<CenovnikStavka>();
		 
		 Lek lek = null;
		 
		 for(Lek l: lekoviApoteke) {
			 for(int i=0; i<listaLekovaICena.size(); i++) {
			 if(l.getSifrarnikLekova().getNaziv().equals(listaLekovaICena.get(i).key)) {
				 
				 CenovnikStavka stavka = new CenovnikStavka(listaLekovaICena.get(i).value, l, cenovnik);
				
				 stavkeLista.add(stavka);
				 
				 
			 }
			 }
		 }
		 
		 cenovnik.setStavkeCenovnika(stavkeLista);
		 
		 cenovnikService.save(cenovnik);
		 
	   
		 CenovnikDTO cenovnikDTO = new CenovnikDTO(cenovnik);
		
		 return new ResponseEntity<CenovnikDTO>(cenovnikDTO, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/sviZaLek") //svi cenovnici nekog leka
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<CenovnikDTO>> findAllCenovnici(@PathVariable Long id, HttpServletRequest request) {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<Lek> lekovi= lekService.findByApotekaId(korisnik.getApoteka().getId());
		
		Lek lek = null;
		
		for(Lek l: lekovi) {
			if(l.getId().equals(id)) {
				lek = l;
			}
		}
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			lekoviDTO.add(new LekDTO(l));
		}	
		return null;
		//return new ResponseEntity<List<CenovnikDTO>>(lekoviDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/najbliziDatum")
	public ResponseEntity<DateTime> najbliziDatum() {
		
	    
	   List<Cenovnik> sviCenovnici = cenovnikService.findAll();
	   List<DateTime> datumi = new ArrayList<DateTime>();
	   
	   for(Cenovnik cenovnik: sviCenovnici) {
		   datumi.add(new DateTime(cenovnik.getInterval().split("/")[1])); //kupi krajnji datum
	   }
	   final long now = System.currentTimeMillis();

	// Get date closest to "now"
	DateTime closest = Collections.min(datumi, new Comparator<DateTime>() {
		@Override
		public int compare(DateTime d1, DateTime d2) {
	        long diff1 = Math.abs(d1.getMillis() - now);
	        long diff2 = Math.abs(d2.getMillis() - now);
	        return Long.compare(diff1, diff2);
	    }

		
	});
			   
		
		return new ResponseEntity<DateTime>(closest, HttpStatus.OK);
	}
	@GetMapping(value = "/proveraDatuma") //provera da li je za ovaj period vec definisan cenovnik, ako jeste- ne moze se definisati novi cenovnik za taj datum, samo menjati
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public boolean proveriCenovnik(@RequestParam("pocetak") String pocetak, @RequestParam("kraj") String kraj, HttpServletRequest request) throws MailException, InterruptedException {
		
		boolean flag = false;
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		Apoteka a = korisnik.getApoteka();
		
		Set<Cenovnik> cenovnici = a.getCenovnici();
	    
		String intervalS = pocetak + ":00.000+01:00" + "/" + kraj + ":00.000+01:00";
		Interval interval = new Interval(intervalS);
		
		for(Cenovnik c: cenovnici) {
			Interval i = new Interval(c.getInterval());
			if(i.overlaps(interval)) {
				
				flag = true;
				break;
			}
		}
			
			return flag;
		
	}
	@GetMapping(value = "/cenovniciOdDanasNadalje") //ne moze da menja prosle cenovnike zbog izvestaja
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<CenovnikDTO>> getCenovnici(HttpServletRequest request) throws MailException, InterruptedException {
		
		boolean flag = false;
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		Apoteka a = korisnik.getApoteka();
		
		Set<Cenovnik> cenovnici = a.getCenovnici();
	    
		List<CenovnikDTO> cenovniciDTO = new ArrayList<CenovnikDTO>();
		
		DateTime danas = new DateTime();
		Interval danasI = new Interval(danas, danas);
		
		for(Cenovnik c: cenovnici) {
			Interval interval = new Interval(c.getInterval());
			if(interval.overlaps(danasI)) {
				cenovniciDTO.add(new CenovnikDTO(c));
			}
		}
			
			return new ResponseEntity<List<CenovnikDTO>>(cenovniciDTO, HttpStatus.OK);
		
	}
	@GetMapping(value = "/getOne") //cenovnik za izmenu
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<CenovnikDTO> findOneCenovnik(@RequestParam("id") Long id) {
		
		Cenovnik c = cenovnikService.findOne(id);
		
		Set<CenovnikStavka> stavke = c.getStavkeCenovnika();
		
		List<CenovnikStavkaDTO> stavkeDTO = new ArrayList<CenovnikStavkaDTO>();
		
		CenovnikDTO cDTO = new CenovnikDTO(c);
		for(CenovnikStavka stavka: stavke) {
			
			stavkeDTO.add(new CenovnikStavkaDTO(stavka));
		}
		
		cDTO.setStavke(stavkeDTO);
		

        return new ResponseEntity<CenovnikDTO>(cDTO, HttpStatus.OK);
	}
static class paroviIzmena{
		public Long id;//id cenovnika koji se menja
		public String key; //naziv leka
		public int value; //cena leka
		public String periodVazenjaOd; 
		public String periodVazenjaDo;
		
	}
	@RequestMapping(value = "/izmeniCenovnik", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<CenovnikDTO> izmenaCenovnika(@RequestBody List<paroviIzmena> listaLekovaICena, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		Cenovnik cenovnikStari = cenovnikService.findOne(listaLekovaICena.get(0).id);
		
		DateTime kraj = new DateTime(listaLekovaICena.get(0).periodVazenjaOd);
		DateTime kraj2 = kraj.minusDays(1);
		System.out.println(kraj2.toString() + " datum podesen");
		 String intervalNovi = listaLekovaICena.get(0).periodVazenjaOd + ":00.000+01:00" + "/" + listaLekovaICena.get(0).periodVazenjaDo + ":00.000+01:00";
		 
		 String intervalStari = cenovnikStari.getInterval().split("/")[0] + "/" + kraj2;
		
		 cenovnikStari.setInterval(intervalStari);
		 cenovnikService.save(cenovnikStari);
		
		 Cenovnik cenovnikNovi = new Cenovnik(intervalNovi, korisnik.getApoteka());
		 cenovnikService.save(cenovnikNovi);
		 Set<CenovnikStavka> stavkeListaStare = cenovnikStari.getStavkeCenovnika();
		 Set<CenovnikStavka> stavkeeee = new HashSet<CenovnikStavka>();
		 
		 for(CenovnikStavka stavka : stavkeListaStare) {
			 CenovnikStavka s = new CenovnikStavka(stavka.getCena(), stavka.getLek(),
						cenovnikNovi);
			 stavkeeee.add(s);
			 
		 }
		 
			 if(!listaLekovaICena.get(0).key.equals("nema izmene")) {
									
				 for(int i=0; i<listaLekovaICena.size(); i++) {
				
					 for(CenovnikStavka stavka : stavkeeee) {
						 if(stavka.getLek().getSifrarnikLekova().getNaziv().equals(listaLekovaICena.get(i).key)) {
							 stavka.setCena(listaLekovaICena.get(i).value);
						 }
					 }
				 }
				
					
			 
			 }
		
		 
		
		 
		 cenovnikNovi.setStavkeCenovnika(stavkeeee);
		 
		 cenovnikService.save(cenovnikNovi);
		 
	   
		 CenovnikDTO cenovnikDTO = new CenovnikDTO(cenovnikNovi);
		
		 return new ResponseEntity<CenovnikDTO>(cenovnikDTO, HttpStatus.OK);
		
	}
}
