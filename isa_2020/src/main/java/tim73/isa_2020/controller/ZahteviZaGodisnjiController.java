package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.KorisnikController.PenalBody;
import tim73.isa_2020.dto.PacijentPodaciDTO;
import tim73.isa_2020.dto.ZahtevZaGodisnjiDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.ZahtevZaGodisnjiService;

@RestController
@RequestMapping(value = "/zahtevi")
public class ZahteviZaGodisnjiController {

	@Autowired
	private ZahtevZaGodisnjiService zahtevService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private EmailService mailService;
	
	@Autowired
	private PregledService pregledService;
	
	@GetMapping("/getNeodobreniZahtevi")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<ZahtevZaGodisnjiDTO>> getZahtevi(HttpServletRequest request){
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<ZahtevZaGodisnji> zahtevi = zahtevService.findByApotekaId(korisnik.getApoteka().getId());
		List<ZahtevZaGodisnji> neodobreni = new ArrayList<ZahtevZaGodisnji>();
		
		for(ZahtevZaGodisnji z: zahtevi) {
			if(z.getStatus().equals("neodobren")) {
				neodobreni.add(z);
			}
		}
		
		List<ZahtevZaGodisnjiDTO> zahteviDTO = new ArrayList<ZahtevZaGodisnjiDTO>();
		
		for(ZahtevZaGodisnji z: neodobreni) {
			if(z.getDermatolog()==null) {
				zahteviDTO.add(new ZahtevZaGodisnjiDTO(z, z.getFarmaceut().getEmail()));
			}else {
				zahteviDTO.add(new ZahtevZaGodisnjiDTO(z, z.getDermatolog().getEmail()));
		}
		}
		return new ResponseEntity<List<ZahtevZaGodisnjiDTO>>(zahteviDTO, HttpStatus.OK);
	}
	static class ZahtevBody{
		public String email;
		public Long id;
	}
		@RequestMapping(value = "/odobri", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<ZahtevZaGodisnjiDTO> odobriZahtev(@RequestBody ZahtevBody zahtev, HttpServletRequest request) throws MailException, InterruptedException {
			
	       ZahtevZaGodisnji zahtev1 = zahtevService.findOne(zahtev.id);
	       
           zahtev1.setStatus("odobren");
		   zahtevService.save(zahtev1);
		   
		   List<Pregled> preglediZaBrisanje = new ArrayList<Pregled>();
		   
		   if(zahtev1.getDermatolog()==null) {
			   Farmaceut f = (Farmaceut) korisnikService.findById(zahtev1.getFarmaceut().getId()); 
			   for(Pregled p: f.getPregledi()) {
				   if(p.getStatus().equals("rezervisan")) {
					   Interval i = new Interval(p.getInterval());
					   Interval godisnji = new Interval(zahtev1.getInterval());
					   if(i.overlaps(godisnji)) {
						   preglediZaBrisanje.add(p);
					   mailService.sendSimpleMessage(p.getPacijent().getEmail(), "OTKAZIVANJE PREGLEDA ", "Vas pregled za termin "
								+ p.getInterval() + " je otkazan jer je lekar tada na godisnjem odmoru.");
					   p.setPacijent(null);
					   p.setFarmaceut(null);
					   p.setApoteka(null);
					   p.setTip(null);
					   pregledService.save(p);
				   }
			   }
			   }
		   }else {
			  Dermatolog d = (Dermatolog) korisnikService.findById(zahtev1.getDermatolog().getId()); 
			  for(Pregled p: d.getPregledi()) {
				   if(p.getStatus().equals("rezervisan")) {
					   Interval i = new Interval(p.getInterval());
					   Interval godisnji = new Interval(zahtev1.getInterval());
					   if(i.overlaps(godisnji)) {
					
					  preglediZaBrisanje.add(p);
					   mailService.sendSimpleMessage(p.getPacijent().getEmail(), "OTKAZIVANJE PREGLEDA ", "Vas pregled za termin "
								+ p.getInterval() + " je otkazan jer je lekar tada na godisnjem odmoru.");
					   p.setPacijent(null);
					   p.setDermatolog(null);
					   p.setApoteka(null);
					   p.setTip(null);
					   pregledService.save(p);
					   
					 
					 
				   }
			   }
			  }
			  
		   }
		   pregledService.delete(preglediZaBrisanje);
		   mailService.sendSimpleMessage(zahtev.email, "Vas zahtev za godisnji odmor je odobren. ", "Od: "
					+ zahtev1.getInterval() + "do: ");
		   
		   ZahtevZaGodisnjiDTO zahtevDTO = new ZahtevZaGodisnjiDTO(zahtev1, zahtev.email);
			
			return new ResponseEntity<ZahtevZaGodisnjiDTO>(zahtevDTO, HttpStatus.OK);
		}
		static class GodisnjiRazlog{
			public String razlog;
			public Long id;
		}
			@RequestMapping(value = "/odbij", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
			@PreAuthorize("hasRole('ADMINISTRATOR')")
			public ResponseEntity<ZahtevZaGodisnjiDTO> oodbijZahtev(@RequestBody GodisnjiRazlog zahtev, HttpServletRequest request) throws MailException, InterruptedException {
				
		       ZahtevZaGodisnji zahtev1 = zahtevService.findOne(zahtev.id);
		       
	           zahtev1.setStatus("odbijen");
	           zahtev1.setRazlogOdbijanja(zahtev.razlog);
			   zahtevService.save(zahtev1);
			   ZahtevZaGodisnjiDTO zahtevDTO = null;
			   if(zahtev1.getDermatolog()==null) {
			   mailService.sendSimpleMessage(zahtev1.getFarmaceut().getEmail(), "Vas zahtev za godisnji odmor je odbijen zbog ", zahtev.razlog);
			   
			               zahtevDTO = new ZahtevZaGodisnjiDTO(zahtev1, zahtev1.getFarmaceut().getEmail());
			   }else {
				   mailService.sendSimpleMessage(zahtev1.getDermatolog().getEmail(), "Vas zahtev za godisnji odmor je odbijen zbog ", zahtev.razlog);
				   
				           zahtevDTO = new ZahtevZaGodisnjiDTO(zahtev1, zahtev1.getDermatolog().getEmail());
			   }
				return new ResponseEntity<ZahtevZaGodisnjiDTO>(zahtevDTO, HttpStatus.OK);
			}
}
