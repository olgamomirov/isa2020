package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;

@RestController
@RequestMapping(value = "/a")
public class ApotekaController {
	
	@Autowired
	private ApotekaService apotekaService;
	
	@GetMapping(value = "/{id}")
	ResponseEntity<ApotekaDTO> findById(@PathVariable long id){
		return new ResponseEntity<>(new ApotekaDTO(apotekaService.findById(id)), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	ResponseEntity<List<ApotekaDTO>> findAll() {
		
		List<Apoteka> apoteke= apotekaService.findAll();
		
		List<ApotekaDTO> apotekeDTO= new ArrayList<>();
		
		for(Apoteka a:apoteke) {
			apotekeDTO.add(new ApotekaDTO(a));
		}
		
		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}
}
