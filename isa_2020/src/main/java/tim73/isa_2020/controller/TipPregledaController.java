package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.PonudaDTO;
import tim73.isa_2020.dto.TipPregledaDTO;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.Ponuda;
import tim73.isa_2020.model.TipPregleda;
import tim73.isa_2020.service.TipPregledaService;

@RestController
@RequestMapping(value = "/tipoviPregleda")
public class TipPregledaController {

	@Autowired
	private TipPregledaService tipService;
	
	@GetMapping(value = "/svi") 
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<TipPregledaDTO>> findAll(HttpServletRequest request) {
		
		List<TipPregleda> tipovi = tipService.findAll();
		
		List<TipPregledaDTO> dto = new ArrayList<TipPregledaDTO>();
		
		for(TipPregleda t: tipovi) {
			dto.add(new TipPregledaDTO(t));
		}
		
		return new ResponseEntity<List<TipPregledaDTO>>(dto, HttpStatus.OK);
	}
}
