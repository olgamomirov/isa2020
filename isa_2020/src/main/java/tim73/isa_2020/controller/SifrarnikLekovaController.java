package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashSet;
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

import tim73.isa_2020.dto.SifrarnikLekovaDTO;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@RequestMapping(value = "/sifrarnik")
public class SifrarnikLekovaController {

	@Autowired
	private SifrarnikLekovaService sifrarnikLekovaService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Set<SifrarnikLekova>> zamene(@PathVariable Long id) {
		
		return new ResponseEntity<Set<SifrarnikLekova>>(sifrarnikLekovaService.getById(id).getZamenskiLekovi(),HttpStatus.OK);
	}
	
	@GetMapping(value="/sviLekovi")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<Set<SifrarnikLekovaDTO>> getSvi() {
		
		
		List<SifrarnikLekova> lekovi = sifrarnikLekovaService.findAll();
		Set<SifrarnikLekovaDTO> sifreLista = new HashSet<SifrarnikLekovaDTO>();
		for(SifrarnikLekova sifra: lekovi) {
		     sifreLista.add(new SifrarnikLekovaDTO(sifra));
		}
		return new ResponseEntity<Set<SifrarnikLekovaDTO>>(sifreLista,HttpStatus.OK);
	}
	
}
