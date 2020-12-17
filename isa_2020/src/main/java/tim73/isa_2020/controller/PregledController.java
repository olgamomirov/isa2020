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
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
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
		Pregled pregled = new Pregled(start, stop, interval, "default", null, null);
		
		DateTime start1=new DateTime(2020, 8, 19, 14, 0, 0);
		DateTime stop1=new DateTime( 2020, 8, 19, 15, 0, 0);
		Interval interval1 = new org.joda.time.Interval( start1, stop1 );
		Pregled pregled1 = new Pregled(start1, stop1, interval1, "default", null, null);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(1);
		Farmaceut farmaceut = farmaceutService.findOne((long)4);
		
		pregled.setDermatolog(dermatolog);
		pregled.setApoteka(apoteka);
		pregledService.save(pregled);
		
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
			preglediDTO.add(new PregledDTO(p));
		}	
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/svi")
	@PreAuthorize("hasRole('FARMACEUT') or hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> findAll(HttpServletRequest request) {
		
		Set<Pregled> pregledi = null;
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		List<Authority> authority = (List<Authority>) k.getAuthorities();
		String role = authority.get(0).getName();
		
		List<PregledDTO> preglediDTO= new ArrayList<>();
		
		if(role.equals("ROLE_DERMATOLOG")) {
			Dermatolog d = (Dermatolog) k;
			pregledi = d.getPregledi();
		}else {
			Farmaceut f = (Farmaceut) k;
			pregledi = f.getPregledi();
		}
		for(Pregled p: pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}
	 
		
		return new ResponseEntity<List<PregledDTO>>(preglediDTO, HttpStatus.OK);
	}

}
