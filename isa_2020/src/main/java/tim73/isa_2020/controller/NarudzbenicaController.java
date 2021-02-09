package tim73.isa_2020.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tim73.isa_2020.controller.CenovnikController.paroviIzmena;
import tim73.isa_2020.controller.KorisnikController.GodisnjiDermatolog;
import tim73.isa_2020.dto.CenovnikDTO;
import tim73.isa_2020.dto.CenovnikStavkaDTO;
import tim73.isa_2020.dto.NarudzbenicaDTO;
import tim73.isa_2020.dto.PonudaDTO;
import tim73.isa_2020.dto.StavkaNarudzbeniceDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.Ponuda;
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
	
	@GetMapping(value = "/sve") //narudzbenice iz apoteke u kojoj adminsitrator radi
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<NarudzbenicaDTO>> findAll(HttpServletRequest request) {
		
		 String token = tokenUtils.getToken(request);
		 String username = this.tokenUtils.getUsernameFromToken(token);
		 AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<Narudzbenica> narudzbenice= narudzbenicaService.findAll();
		
		List<Narudzbenica> narudzbeniceApoteke = new ArrayList<Narudzbenica>();
		
		for(Narudzbenica n: narudzbenice) {
			if(n.getApoteka().getId().equals(admin.getApoteka().getId())) {
				narudzbeniceApoteke.add(n);
			}
			
		}
		
        List<NarudzbenicaDTO> narudzbeniceDTO= new ArrayList<>();
		
		for(Narudzbenica n: narudzbeniceApoteke) {
			if(n.getPonude().size()<=0) {
			narudzbeniceDTO.add(new NarudzbenicaDTO(n, true));
			}else {
			narudzbeniceDTO.add(new NarudzbenicaDTO(n, false));	
			}
		}	
		
		return new ResponseEntity<List<NarudzbenicaDTO>>(narudzbeniceDTO, HttpStatus.OK);
	}
	
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
			 
			 Narudzbenica narudzbenica  = new Narudzbenica(rok,"ceka ponude", a, admin);
			 
			 
		
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
		static class iD{
			public Long id;
		}
		@RequestMapping(value = "/obrisi", method = RequestMethod.POST, consumes = "application/json")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public String brisanjeNarudzbenice(@RequestBody iD id, HttpServletRequest request) {
			
			 String token = tokenUtils.getToken(request);
			 String username = this.tokenUtils.getUsernameFromToken(token);
			 AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
			 
			 Apoteka a = admin.getApoteka();
			 
			 Narudzbenica n = narudzbenicaService.findOne(id.id);
			 
			
			 narudzbenicaService.delete(n);
			 
	
			 return "e";
		}
		@GetMapping(value = "/getOne") //narudzbenica za izmenu
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<NarudzbenicaDTO> findOneNarudzbenica(@RequestParam("id") Long id) {
			
			Narudzbenica n = narudzbenicaService.findOne(id);
			
			Set<StavkaNarudzbenice> stavke = n.getStavkeNarudzbenice();
			
			List<StavkaNarudzbeniceDTO> stavkeDTO = new ArrayList<StavkaNarudzbeniceDTO>();
			
			NarudzbenicaDTO nDTO = new NarudzbenicaDTO(n, false);
			
			for(StavkaNarudzbenice stavka: stavke) {
				
				stavkeDTO.add(new StavkaNarudzbeniceDTO(stavka));
			}
			
			nDTO.setStavke(stavkeDTO);
			

	        return new ResponseEntity<NarudzbenicaDTO>(nDTO, HttpStatus.OK);
		}
		static class paroviIzmena{
			public Long id;//id narudzbenice koja se menja
			public String key; //naziv leka
			public int value; //kolicina leka
			public String periodVazenjaOd; 
			
			
		}
		@RequestMapping(value = "/izmeniNarudzbenicu", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
		@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<NarudzbenicaDTO> izmenaCNarudzbenice(@RequestBody List<paroviIzmena> listaLekovaIKolicina, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
			
			
			Narudzbenica nStara = narudzbenicaService.findOne(listaLekovaIKolicina.get(0).id);
			
			
			 String intervalNovi = listaLekovaIKolicina.get(0).periodVazenjaOd + ":00.000+01:00";
			 
			nStara.setRokPonude(intervalNovi);
			
			 Set<StavkaNarudzbenice> stavkeListaStare = nStara.getStavkeNarudzbenice();
			 
			 
			 for(StavkaNarudzbenice stavka: stavkeListaStare) {
				 if(listaLekovaIKolicina.size()!=0) {
					 for(int i=0; i<listaLekovaIKolicina.size(); i++) {
				 if(stavka.getLek().getSifrarnikLekova().getNaziv().equals(listaLekovaIKolicina.get(i).key)) {
					 stavka.setKolicina(listaLekovaIKolicina.get(i).value);
					 stavkaNarudzbeniceService.save(stavka);
				 }
			 }
				
			 }
			 }
			 
			narudzbenicaService.save(nStara);
			 
		   NarudzbenicaDTO dto = new NarudzbenicaDTO(nStara, false);
			
			 return new ResponseEntity<NarudzbenicaDTO>(dto, HttpStatus.OK);
			
		}
}
