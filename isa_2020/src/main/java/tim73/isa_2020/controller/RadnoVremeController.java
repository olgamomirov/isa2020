package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		DateTime start=new DateTime( 2021, 1, 13, 14, 0, 0);
		DateTime stop=new DateTime( 2021, 1, 20, 15, 0, 0);
		Interval interval = new Interval( start, stop );
		RadnoVreme rv=new RadnoVreme( interval);
		
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
	@GetMapping(value = "/getRadnoVreme")
	ResponseEntity<List<RadnoVremeDTO>> get(){
		
		
		Dermatolog dermatolog= dermatologService.findById(2);
		
		
		//System.out.println(apoteka.getDermatolozi().contains(dermatolog));
		List<RadnoVremeDTO> preglediDTO= new ArrayList<>();
		//dermatolog.setRadnoVreme(rvs);
		//dermatologService.save(dermatolog); 
		ArrayList<RadnoVremeDTO> rvDTO = new ArrayList<RadnoVremeDTO>();
		Set<RadnoVreme> rv =  new HashSet<RadnoVreme>();
		for(RadnoVreme radno: dermatolog.getRadnoVreme()) {
			System.out.println(radno.getInterval().getStart().getDayOfMonth());
			  rv.add(radno);
		}
		for(RadnoVreme radno: rv) {
		 preglediDTO.add(new RadnoVremeDTO(radno.getInterval()));
		}
		return new ResponseEntity<List<RadnoVremeDTO>>(preglediDTO, HttpStatus.OK);
	}
}
