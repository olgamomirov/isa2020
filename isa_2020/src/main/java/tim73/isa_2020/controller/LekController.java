package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.PacijentService;

@RestController
@RequestMapping(value = "/lekovi")
public class LekController {

	@Autowired
	private LekService lekService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@GetMapping(value = "/sviLekovi")
	public ResponseEntity<List<LekDTO>> findAll() {
		
		List<Lek> lekovi= lekService.findAll();
		
		List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			lekoviDTO.add(new LekDTO(l));
		}
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<LekDTO>> findOne(@PathVariable Long id) {
		
		List<Lek> lekovi= lekService.findByApotekaId(id);
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			lekoviDTO.add(new LekDTO(l));
		}	
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pacijent/{id}")
	public ResponseEntity<List<LekDTO>> findAlergije(@PathVariable Long id) {
		
		Set<Lek> lekovi= pacijentService.findById(id).getAlergija().getLekovi();
		
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			
			lekoviDTO.add(new LekDTO(l));
		}
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
}
