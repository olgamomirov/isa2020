package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/a")
public class ApotekaController {
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApotekaDTO> findById(@PathVariable long id){
		return new ResponseEntity<>(new ApotekaDTO(apotekaService.findById(id)), HttpStatus.OK);
	}

	
	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<ApotekaDTO>> findAll() {
		
		List<Apoteka> apoteke= apotekaService.findAll();
		
		List<ApotekaDTO> apotekeDTO= new ArrayList<>();
		
		for(Apoteka a:apoteke) {
			apotekeDTO.add(new ApotekaDTO(a));
		}
		
		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/sve")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<ApotekaDTO>> findAllD(HttpServletRequest request) {
		
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik k = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		
		Dermatolog d = (Dermatolog) k;
		
		List<Apoteka> apoteke= new ArrayList<>();
		
		List<ApotekaDTO> apotekeDTO= new ArrayList<>();
		
		for(Apoteka a: d.getApoteke()) {
			apoteke.add(a);
		}
		
		for(Apoteka a:apoteke) {
			apotekeDTO.add(new ApotekaDTO(a));
		}
		
		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{apotekaId}")
	public void deleteApoteka(@PathVariable long apotekaId) {
		
		apotekaService.delete(apotekaId);
		
	}
	@PostMapping(value = "/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void add(@RequestBody Apoteka apoteka){

		Apoteka apoteka1 = new Apoteka();
		apoteka1.setNaziv(apoteka.getNaziv());
		apotekaService.save(apoteka1);
		
	}
	//za profil apoteke
	@GetMapping(value = "/apoteka/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<ApotekaDTO> detaljiApoteke(@PathVariable long id){
		Apoteka apoteka=apotekaService.findById(id);
		ApotekaDTO apotekaDTO=new ApotekaDTO(apoteka);
		return new ResponseEntity<ApotekaDTO>(apotekaDTO, HttpStatus.OK);
		
	}
	
	//za profil apoteke
	@GetMapping(value = "/lekari/apoteka/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> lekariApoteke(@PathVariable long id) {
		Apoteka apoteka = apotekaService.findById(id);
		List<LekarDTO> lekari = new ArrayList<LekarDTO>();
		for (Dermatolog d : apoteka.getDermatolozi()) {
			if (d != null) {
				lekari.add(new LekarDTO(d.getIme() + " " + d.getPrezime(), "dermatolog"));
			}
		}
		for (Farmaceut f : apoteka.getFarmaceuti()) {
			if (f != null) {
				lekari.add(new LekarDTO(f.getIme() + " " + f.getPrezime(), "farmaceut"));
			}
		}

		return new ResponseEntity<List<LekarDTO>>(lekari, HttpStatus.OK);
	}

}
