package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.service.KorisnikService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/korisnici")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@PostMapping(value = "/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void login(@RequestBody Korisnik korisnik) {
		
		System.out.println(korisnik.getEmail());
		System.out.println(korisnik.getLozinka());
		
       if((korisnikService.findByEmailAndLozinka(korisnik.getEmail(), korisnik.getLozinka()))!=null) {
    	   System.out.println("korisnik se uspesno logovao jeeeeeeea!");
       }
       else {
    	   System.out.println("nema korisnika");
       }
		
		
	}

}
