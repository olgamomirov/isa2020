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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.dto.RadnoVremeDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.RadnoVremeService;

@RestController
@RequestMapping(value = "/radnoVreme")
public class RadnoVremeController {

	@Autowired
	private RadnoVremeService radnoVremeService;
	
	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private ApotekaService apotekaService;
	

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikServiceImpl korisnikDetails;

	/*
>>>>>>> refs/remotes/origin/main
	
	@GetMapping(value = "/add")
	ResponseEntity<String> add(){
		DateTimeZone timeZone = DateTimeZone.forID( "Europe/Paris" );

		DateTime start=new DateTime( 2021, 1, 13, 14, 0, 0);
		DateTime stop=new DateTime( 2021, 1, 13, 17, 0, 0);
		Interval interval = new Interval( start, stop );
		RadnoVreme rv=new RadnoVreme( interval);
		
		DateTime start14=new DateTime( 2021, 1, 14, 14, 0, 0);
		DateTime stop14=new DateTime( 2021, 1, 14, 17, 0, 0);
		Interval interval14 = new Interval( start14, stop14 );
		RadnoVreme rv1=new RadnoVreme( interval14);
		
		DateTime start15=new DateTime( 2021, 1, 15, 14, 0, 0);
		DateTime stop15=new DateTime( 2021, 1, 15, 17, 0, 0);
		Interval interval15 = new Interval( start15, stop15 );
		RadnoVreme rv2=new RadnoVreme( interval15);
		
		Set<RadnoVreme> rvs= new HashSet<>();
		rvs.add(rv);
		rvs.add(rv1);
		rvs.add(rv2);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(1);
		
		//System.out.println(apoteka.getDermatolozi().contains(dermatolog));
		
		//dermatolog.setRadnoVreme(rvs);
		//dermatologService.save(dermatolog); 
		
		rv.setDermatolog(dermatolog);
		rv.setApoteka(apoteka);
		rv1.setDermatolog(dermatolog);
		rv1.setApoteka(apoteka);
		rv2.setDermatolog(dermatolog);
		rv2.setApoteka(apoteka);
		radnoVremeService.save(rv);
		radnoVremeService.save(rv1);
		radnoVremeService.save(rv2);
		return new ResponseEntity<>(rv.toString(), HttpStatus.OK);
	}
	*/
	@GetMapping(value = "/getRadnoVreme")
	ResponseEntity<List<RadnoVremeDTO>> get(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.korisnikDetails.loadUserByUsername(username);
		
		Dermatolog dermatolog= (Dermatolog) k;
		
		
		List<RadnoVremeDTO> radnoVremeDTO= new ArrayList<>();
		
		Set<RadnoVreme> rv =  new HashSet<RadnoVreme>();
		for(RadnoVreme radno: dermatolog.getRadnoVreme()) {
			Interval interval = new Interval(radno.getInterval());
			System.out.println(interval.getStart().getDayOfMonth());
			  rv.add(radno);
		}
		for(RadnoVreme radno: rv) {
		 radnoVremeDTO.add(new RadnoVremeDTO(radno));
		}
		return new ResponseEntity<List<RadnoVremeDTO>>(radnoVremeDTO, HttpStatus.OK);
	}
}
