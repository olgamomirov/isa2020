package tim73.isa_2020.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tim73.isa_2020.controller.KorisnikController.GodisnjiDermatolog;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.model.StavkaNarudzbenice;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.NarudzbenicaService;
import tim73.isa_2020.service.SifrarnikLekovaService;
import tim73.isa_2020.service.StavkaNarudzbeniceService;

@RestController
@RequestMapping(value = "/narudzbenice")
//@CrossOrigin(origins = "http://localhost:3000")
public class NarudzbenicaController {

	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private StavkaNarudzbeniceService stavkaNarudzbeniceService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private SifrarnikLekovaService sifrarnikService;
	
	static class parovi{
		
		public String key;
		public int value;
		public String rok; //rok je na nivou cele narudzbenice, ali se ovde salje isti datum uz svaku stavku narudzbenice
		
	}
  
		@RequestMapping(value = "/definisiNarudzbenicu", method = RequestMethod.POST, consumes = "application/json")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public String definisiNarudzbenicu(@RequestBody List<parovi> parovi, HttpServletRequest request) throws MailException, InterruptedException {
			
			 String token = tokenUtils.getToken(request);
			 String username = this.tokenUtils.getUsernameFromToken(token);
			 AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
			 
			 Apoteka a = admin.getApoteka();
			
			 Set<Lek> lekoviApoteke = a.getLekovi();
			
			 Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<StavkaNarudzbenice>();
			
			 String rok = parovi.get(0).rok + ":00.000+01:00";
			 
			 Narudzbenica narudzbenica  = new Narudzbenica(rok, a);
			 
			 
		
	     for(Lek l: lekoviApoteke) {
	    	 for(int i=0; i<parovi.size(); i++) {
			if(l.getSifrarnikLekova().getNaziv().equals(parovi.get(i).key)) {
	     
	     StavkaNarudzbenice stavka = new StavkaNarudzbenice(l, parovi.get(i).value, narudzbenica);
	     stavkeNarudzbenice.add(stavka);
	     parovi.remove(i);
	     }}
	     }
	     //narucuje lek koji do sad nije bio na stanju
	    	 for(SifrarnikLekova sifra: sifrarnikService.findAll()) {
	    		 for(int i=0; i<parovi.size(); i++) {
	    		 if(sifra.getNaziv().equals(parovi.get(i).key)) {
	    	 Lek noviLek = new Lek(0, a, sifra);
	    	 lekService.save(noviLek);
	    	 StavkaNarudzbenice stavka = new StavkaNarudzbenice(noviLek, parovi.get(i).value, narudzbenica);
		     stavkeNarudzbenice.add(stavka);
	    	 }
	    	 }
	    	 }
		
		
	    narudzbenica.setStavkeNarudzbenice(stavkeNarudzbenice);
		 
		narudzbenicaService.save(narudzbenica);
		 
			
		 return admin.getIme();
		}
}
