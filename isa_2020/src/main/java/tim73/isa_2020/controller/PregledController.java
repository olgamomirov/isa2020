package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;

@RestController
@RequestMapping(value = "/pregledi")
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
	
	@GetMapping(value = "/addPregled")
	ResponseEntity<String> add(){
		

		DateTime start=new DateTime(2020, 8, 15, 14, 0, 0);
		DateTime stop=new DateTime( 2020, 8, 15, 15, 0, 0);
		Interval interval = new org.joda.time.Interval( start, stop );
		Pregled pregled = new Pregled(start, stop, interval, "odradjen", null, null);
		
		DateTime start2=new DateTime(2020, 11, 20, 14, 0, 0);
		DateTime stop2=new DateTime( 2020, 11, 20, 16, 0, 0);
		Interval interval2 = new org.joda.time.Interval( start2, stop2 );
		Pregled pregled2 = new Pregled(start2, stop2, interval2, "default", null, null);
		
		DateTime start1=new DateTime(2020, 8, 19, 14, 0, 0);
		DateTime stop1=new DateTime( 2020, 8, 19, 15, 0, 0);
		Interval interval1 = new org.joda.time.Interval( start1, stop1 );
		Pregled pregled1 = new Pregled(start1, stop1, interval1, "default", null, null);
		
		DateTime start3=new DateTime(2020, 11, 15, 14, 0, 0);
		DateTime stop3=new DateTime( 2020, 11, 15, 15, 0, 0);
		Interval interval3 = new org.joda.time.Interval( start3, stop3 );
		Pregled pregled3 = new Pregled(start3, stop3, interval3, "odradjen", null, null);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(2);
		Apoteka apoteka3= apotekaService.findById(3);
		Farmaceut farmaceut = farmaceutService.findOne((long)4);
		Pacijent pacijent = pacijentService.findById((long)3);
		Pacijent pacijent2 = pacijentService.findById((long)5);
		
		pregled.setDermatolog(dermatolog);
		pregled.setApoteka(apoteka);
		pregled.setPacijent(pacijent2);
		pregledService.save(pregled);
		
		pregled3.setDermatolog(dermatolog);
		pregled3.setApoteka(apoteka3);
		pregled3.setPacijent(pacijent);
		pregledService.save(pregled3);
		
		pregled2.setDermatolog(dermatolog);
		pregled2.setApoteka(apoteka);
		pregledService.save(pregled2);
		
		pregled1.setFarmaceut(farmaceut);
		pregled1.setApoteka(apoteka);
		pregledService.save(pregled1);
		
		return new ResponseEntity<>(pregled.toString(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	ResponseEntity<List<PregledDTO>> findOne(@PathVariable Long id) {
		
		List<Pregled> pregledi= pregledService.findByApotekaId(id);
		
        List<PregledDTO> preglediDTO= new ArrayList<>();
		
		for(Pregled p: pregledi) {
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}	
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/svi/{id}")
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
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}else {
			preglediDTO.add(new PregledDTO(p));
		}
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
			preglediDTO.add(new PregledDTO(p, p.getPacijent().getIme(), p.getPacijent().getPrezime()));
		}else {
			preglediDTO.add(new PregledDTO(p));
		}
		}
	 
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}

}
