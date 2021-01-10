package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.LekZaAlergijeDTO;
import tim73.isa_2020.model.Alergije;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@RequestMapping(value = "/lekovi")
public class LekController {

	@Autowired
	private LekService lekService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@Autowired
	private SifrarnikLekovaService sifrarnikLekovaService;

	
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
	/*
	@GetMapping(value = "/pacijent/{id}")
	public ResponseEntity<List<LekDTO>> findAlergije(@PathVariable Long id) {
		
		Set<Lek> lekovi= pacijentService.findById(id).getAlergija().getLekovi();
		
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			
			lekoviDTO.add(new LekDTO(l));
		}	
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
	*/
	
	
	@GetMapping(value = "/jedinstveniNazivi")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<ArrayList<LekZaAlergijeDTO>> jedinstveniNaziviLekova (){
		List<SifrarnikLekova> sviLekovi = sifrarnikLekovaService.findAll();
		ArrayList<LekZaAlergijeDTO> lekoviDTO = new ArrayList<LekZaAlergijeDTO>();
		for (SifrarnikLekova sl : sviLekovi) {
			lekoviDTO.add(new LekZaAlergijeDTO(sl.getNaziv()));
		}	
		return new ResponseEntity<ArrayList<LekZaAlergijeDTO>>(lekoviDTO, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/spisakZaAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<ArrayList<LekZaAlergijeDTO>> jedinstveniNaziviLekova (@RequestBody List<LekZaAlergijeDTO> lekoviDTO, HttpServletRequest request){
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent pacijent = (Pacijent) this.userDetailsService.loadUserByUsername(username);
		
		Set<SifrarnikLekova> lekoviZaAlergije= new HashSet<SifrarnikLekova>();
		
		for(LekZaAlergijeDTO lek:lekoviDTO) {
				lekoviZaAlergije.add(sifrarnikLekovaService.findByNaziv(lek.getNaziv()));	
		}
		if (pacijent.getAlergija() != null) {
			pacijent.getAlergija().setLekovi(lekoviZaAlergije);
		} else {
			Alergije alergije = new Alergije();
			pacijent.setAlergija(alergije);
			pacijent.getAlergija().setLekovi(lekoviZaAlergije);

		}
				
		pacijentService.save(pacijent);
		return null;
	}
	
	
	
	
	@GetMapping(value = "/imaAlergije")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<ArrayList<LekZaAlergijeDTO>> imaAlergije (){
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();		
		Pacijent pacijent=(Pacijent) userDetailsService.findByEmail(tokenUtils.getUsernameFromToken(authentication.getCredentials().toString()));
		
		ArrayList<LekZaAlergijeDTO> lekZaAlegijeDTO= new ArrayList<LekZaAlergijeDTO>();
		if (pacijent.getAlergija() != null) {
			for (SifrarnikLekova sl : pacijent.getAlergija().getLekovi()) {
				lekZaAlegijeDTO.add(new LekZaAlergijeDTO(sl.getNaziv()));
			}
		}	
		return new ResponseEntity<ArrayList<LekZaAlergijeDTO>>(lekZaAlegijeDTO, HttpStatus.OK);
	}
		
	
	

}
