package tim73.isa_2020.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.RezervacijaDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.RezervacijaService;

@RestController
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
			if(f.getApoteka().equals(rezervacija.getLek().getApoteka())) {
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
		
		rezervacijaService.save(rezervacija);
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
		
		Pacijent pacijent = rezervacija.getPacijent();
		
		
		
		mailService.sendSimpleMessage(pacijent.getEmail(), "Potvrda", "Uspesno ste pruzeli lek: "
				+ rezervacija.getLek().getSifrarnikLekova().getNaziv() + " u apoteci: " + rezervacija.getLek().getApoteka().getNaziv());
				
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
}
