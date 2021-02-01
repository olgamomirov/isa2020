package tim73.isa_2020.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.CenovnikDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.CenovnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;


@RestController
@RequestMapping(value = "/cenovnici")
public class CenovnikController {
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	static class CenovnikNovi{
		public double cena;
		public Long idLek;
		public String pocetakVazenja;
		public String krajVazenja;
	}
	@RequestMapping(value = "/dodajCenovnik", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<CenovnikDTO> definisanjeCenovnika(@RequestBody CenovnikNovi cenovnikNovi, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		 String interval = cenovnikNovi.pocetakVazenja + ":00.000+01:00" + "/" + cenovnikNovi.krajVazenja + ":00.000+01:00";
		 
		 List<Lek> lekoviApoteke = lekService.findByApotekaId(korisnik.getApoteka().getId());
		 
		 Lek lek = null;
		 
		 for(Lek l: lekoviApoteke) {
			 if(l.getId()==cenovnikNovi.idLek) {
				 lek = l;
			 }
		 }
		 
	     Cenovnik cenovnik = new Cenovnik(cenovnikNovi.cena, interval, lek);
		 cenovnikService.save(cenovnik);
		 
		 CenovnikDTO cenovnikDTO = new CenovnikDTO(cenovnik);
		 
		 return new ResponseEntity<CenovnikDTO>(cenovnikDTO, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/sviZaLek") //svi cenovnici nekog leka
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<CenovnikDTO>> findAllCenovnici(@PathVariable Long id, HttpServletRequest request) {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<Lek> lekovi= lekService.findByApotekaId(korisnik.getApoteka().getId());
		
		Lek lek = null;
		
		for(Lek l: lekovi) {
			if(l.getId().equals(id)) {
				lek = l;
			}
		}
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			lekoviDTO.add(new LekDTO(l));
		}	
		return null;
		//return new ResponseEntity<List<CenovnikDTO>>(lekoviDTO, HttpStatus.OK);
	}

}
