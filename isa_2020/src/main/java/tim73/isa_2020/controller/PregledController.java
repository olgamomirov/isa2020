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

import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.PregledService;

@RestController
@RequestMapping(value = "/pregledi")
public class PregledController {
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@GetMapping(value = "/addPregled")
	ResponseEntity<String> add(){
		

		DateTime start=new DateTime(2021, 8, 13, 14, 0, 0);
		DateTime stop=new DateTime( 2021, 8, 13, 15, 0, 0);
		Interval interval = new org.joda.time.Interval( start, stop );
		Pregled pregled = new Pregled(start, stop, interval, "default", null, null);
		
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(1);
		
		
		pregled.setDermatolog(dermatolog);
		pregled.setApoteka(apoteka);
		pregledService.save(pregled);
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

}
