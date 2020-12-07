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
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.RezervacijaDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.service.RezervacijaService;

@RestController
@RequestMapping(value = "/rezervacije")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@GetMapping(value = "/{id}")
	ResponseEntity <RezervacijaDTO> findOne(@PathVariable Long id) {
		
		Rezervacija rezervacija = rezervacijaService.findOne(id);
		
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
		
		return new ResponseEntity<RezervacijaDTO>(rezervacijaDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/pacijent/{idPacijent}")
	ResponseEntity <List<RezervacijaDTO>> findOne1(@PathVariable Long idPacijent) {
		
		List<Rezervacija> rezervacije = rezervacijaService.findByPacijentId(idPacijent);
		

		
		List<RezervacijaDTO> rezervacijeDTO= new ArrayList<>();
		
		for(Rezervacija r: rezervacije) {
			rezervacijeDTO.add(new RezervacijaDTO(r));
		}
		
		return new ResponseEntity<List<RezervacijaDTO>>(rezervacijeDTO, HttpStatus.OK);
	}
}
