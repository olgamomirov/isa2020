package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.LekService;

@RestController
@RequestMapping(value = "/svi")
public class NeautentifikovaniController {
//proba
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	
	@GetMapping(value = "/lekovi/{id}")
	public ResponseEntity<List<LekDTO>> lekoviApoteke(@PathVariable Long id) {
		
		List<Lek> lekovi= lekService.findByApotekaId(id);
		
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			
			lekoviDTO.add(new LekDTO(l));
		}
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/apoteke")
	public ResponseEntity<List<ApotekaDTO>> findAll() {
		
		List<Apoteka> apoteke= apotekaService.findAll();
		
		List<ApotekaDTO> apotekeDTO= new ArrayList<>();
		
		for(Apoteka a:apoteke) {
			apotekeDTO.add(new ApotekaDTO(a));
		}
		
		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/lekovi/{id}/{pretraga}")
	public ResponseEntity<List<LekDTO>> lekoviPretraga(@PathVariable String pretraga, @PathVariable Long id) {
		
		List<Lek> lekovi= lekService.findByApotekaId(id);
		

		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			if(l.getNaziv().contains(pretraga)) {
				lekoviDTO.add(new LekDTO(l));
			}
		}
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
}
