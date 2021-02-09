package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.NarudzbenicaDTO;
import tim73.isa_2020.dto.UpitZaLekDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.UpitZaLek;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.UpitZaLekService;

@RestController
@RequestMapping(value = "/upiti")
public class UpitZaLekController {

	@Autowired 
	private UpitZaLekService upitService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	
	@GetMapping(value = "/svi") //administratoru apoteke na prikaz svi upiti koji nisu pregledani za lek koje je lekar poslao
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<UpitZaLekDTO>> findAll(HttpServletRequest request) {
		
		 String token = tokenUtils.getToken(request);
		 String username = this.tokenUtils.getUsernameFromToken(token);
		 AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		 Apoteka a = admin.getApoteka();
		 
		 List<UpitZaLek> upitiSvi = upitService.findAll();
		 
		 List<UpitZaLekDTO> upitiIzApoteke = new ArrayList<UpitZaLekDTO>();
		 
		 for(UpitZaLek u: upitiSvi) {
		   if(u.getLek().getApoteka().getId().equals(a.getId())) {
			   if(u.getStatus().equals("nepregledan")) {
				   upitiIzApoteke.add(new UpitZaLekDTO(u));
			   }
		   }
			 
		 }
		 
		return new ResponseEntity<List<UpitZaLekDTO>>(upitiIzApoteke, HttpStatus.OK);
	}
	
}
