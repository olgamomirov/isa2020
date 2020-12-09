package tim73.isa_2020.controller;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
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
	
	
	@GetMapping(value = "/add")
	ResponseEntity<String> add(){
		DateTimeZone timeZone = DateTimeZone.forID( "Europe/Paris" );

		DateTime start=new DateTime( null, timeZone);
		DateTime stop=new DateTime( 2021, 8, 13, 14, 0, 0,timeZone);
		RadnoVreme rv=new RadnoVreme(start,stop , new org.joda.time.Interval( start, stop ));
		
		Set<RadnoVreme> rvs= new HashSet<>();
		rvs.add(rv);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(1);
		
		//System.out.println(apoteka.getDermatolozi().contains(dermatolog));
		
		//dermatolog.setRadnoVreme(rvs);
		//dermatologService.save(dermatolog); 
		
		rv.setDermatolog(dermatolog);
		rv.setApoteka(apoteka);
		radnoVremeService.save(rv);
		return new ResponseEntity<>(rv.toString(), HttpStatus.OK);
	}
}
