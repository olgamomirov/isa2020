package tim73.isa_2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;

@RestController
@RequestMapping(value = "/dermatolozi")
public class DermatologController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@GetMapping(value = "{id}")
	ResponseEntity<Dermatolog> findById(@PathVariable long id){
		return new ResponseEntity<>(dermatologService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/changeData", consumes="application/json", produces = "application/json")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> changeData(@RequestBody Korisnik korisnik, HttpServletRequest request){
		

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Korisnik userEdited = ulogovan;
		userEdited.setIme(korisnik.getIme());
		userEdited.setPrezime(korisnik.getPrezime());
		userEdited.setEmail(korisnik.getEmail());
		userEdited.setUlica(korisnik.getUlica());
		userEdited.setGrad(korisnik.getGrad());
		userEdited.setDrzava(korisnik.getDrzava());
		userEdited.setTelefon(korisnik.getTelefon());
		korisnikService.save(userEdited);
		
		Dermatolog d = (Dermatolog) userEdited;
		
		DermatologDTO dto = new DermatologDTO(d);
		
		return new ResponseEntity<DermatologDTO>(dto, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getData")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> getData(HttpServletRequest request){
		

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		DermatologDTO dermatologDTO = new DermatologDTO(ulogovan);
		
		return new ResponseEntity<DermatologDTO>(dermatologDTO, HttpStatus.OK);
	}
	
}
