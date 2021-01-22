package tim73.isa_2020.controller;

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

import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.PregledZaPacijentaDTO;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@RequestMapping(value = "/sifrarnik")
public class SifrarnikLekovaController {

	@Autowired
	private SifrarnikLekovaService sifrarnikLekovaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Set<SifrarnikLekova>> zamene(@PathVariable Long id) {
		
		return new ResponseEntity<Set<SifrarnikLekova>>(sifrarnikLekovaService.getById(id).getZamenskiLekovi(),HttpStatus.OK);
	}
	
	
	
}
