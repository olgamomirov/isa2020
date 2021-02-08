package tim73.isa_2020.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.RezervacijaController.Body;
import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.dto.LekZaAlergijeDTO;
import tim73.isa_2020.dto.NoviLekDTO;
import tim73.isa_2020.dto.RezervacijaDTO;
import tim73.isa_2020.dto.SifrarnikLekovaDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Alergije;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.OcenaSifrarnikLekova;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.model.UpitZaLek;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.OcenaSifrarnikLekovaService;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RezervacijaService;
import tim73.isa_2020.service.SifrarnikLekovaService;
import tim73.isa_2020.service.UpitZaLekService;

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
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private EmailService mailService;
	
	@Autowired
	private UpitZaLekService upitService;
	
	
	@Autowired
	private OcenaSifrarnikLekovaService ocenaSifrarnikLekovaService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@GetMapping(value = "/sviLekovi")
	public ResponseEntity<List<LekDTO>> findAll() {
		
		List<Lek> lekovi= lekService.findAll();
		
		List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for(Lek l: lekovi) {
			double ocena=0;
			double brojOcena=0;
			for(OcenaSifrarnikLekova ocenaLeka :ocenaSifrarnikLekovaService.findBySifrarnikLekovaId(l.getSifrarnikLekova().getId())){
				ocena+=ocenaLeka.getVrednost();
				brojOcena++;
			}
			ocena=ocena/brojOcena;
			lekoviDTO.add(new LekDTO(l,ocena));
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
	@GetMapping(value = "/sviIzAdministratoveApoteke") //svi lekovi iz apoteke u kojoj radi administrator
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekDTO>> findAllFrom(HttpServletRequest request) {
	
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke korisnik = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<Lek> lekovi= lekService.findByApotekaId(korisnik.getApoteka().getId());
		
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
	@PreAuthorize("hasRole('PACIJENT') or hasRole('DERMATOLOG')")
	public ResponseEntity<ArrayList<LekZaAlergijeDTO>> jedinstveniNaziviLekova (){
		List<SifrarnikLekova> sviLekovi = sifrarnikLekovaService.findAll();
		ArrayList<LekZaAlergijeDTO> lekoviDTO = new ArrayList<LekZaAlergijeDTO>();
		for (SifrarnikLekova sl : sviLekovi) {
			lekoviDTO.add(new LekZaAlergijeDTO(sl.getNaziv()));
		}	
		return new ResponseEntity<ArrayList<LekZaAlergijeDTO>>(lekoviDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/jedinstveniNazivi/bezAlergija")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<ArrayList<LekZaAlergijeDTO>> jedinstveniNaziviBezAlergija (@RequestParam("email") String email){
		List<SifrarnikLekova> sviLekovi = sifrarnikLekovaService.findAll();
		ArrayList<LekZaAlergijeDTO> lekoviDTO = new ArrayList<LekZaAlergijeDTO>();
		Pacijent p = (Pacijent) userDetailsService.findByEmail(email);
		Set<SifrarnikLekova> sviLekoviAlergija = new HashSet<SifrarnikLekova>();
		if(p.getAlergija()!=null) {
			sviLekoviAlergija = p.getAlergija().getLekovi();
		}
		
		boolean flag; //nije alergican na lek
		for(SifrarnikLekova sl : sviLekovi) {
			flag = false;
			for (SifrarnikLekova lekovi: sviLekoviAlergija) {
			if(lekovi.getId().equals(sl.getId())){
				flag = true;
				break;
			}
				
			
		}
			if(flag==false) {
			lekoviDTO.add(new LekZaAlergijeDTO(sl.getNaziv()));
		}	
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
		
	@GetMapping(value = "/lek/{nazivLeka}/{pregledID}")
	public ResponseEntity<LekDTO> findOne(@PathVariable String nazivLeka, @PathVariable long pregledID) {
		
		Apoteka apoteka = pregledService.findOne(pregledID).getApoteka();
		
		Set<Lek> lekApoteka = apoteka.getLekovi();
		
		 LekDTO lekDTO= null;
		
		for(Lek l: lekApoteka) {
			if(l.getSifrarnikLekova().getNaziv().equals(nazivLeka)) {
				lekDTO = new LekDTO(l);
			}
		}
		
		
		return new ResponseEntity<LekDTO>(lekDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/dostupnost/{naziv}/{id}") //lekar proverava da li je trazeni lek dostupan u apoteci u kojoj vrsi pregled
	public boolean proveriDostupnost(@PathVariable String naziv, @PathVariable long id) throws MailException, InterruptedException {
		
		boolean flag = false;
		
		Pregled p = pregledService.findOne(id);
		
		
		Apoteka a = p.getApoteka();
		
		Set<Lek> lekovi = a.getLekovi();
		
		
		
		for(Lek l: lekovi) {
			flag = false;
			if(l.getSifrarnikLekova().getNaziv().equals(naziv)) { // da li uopste prodaju taj lek u apoteci
				if(l.getKolicina()>0) {
					
					return true;
				}
				
				
			}
			
		}
		Set<AdministratorApoteke> admini = a.getAdministratorApoteke();
		if(!flag) {
			String email = null;
		for(AdministratorApoteke admin: admini) {
			 email = admin.getEmail();  //salje svim administratorima apoteke (ukoliko ih ima vise)
			 mailService.sendSimpleMessage(email, "NEDOSTUPAN LEK", "Lek  "
						+ naziv + " nije dostupan.");
		}
		
		}
		
		Lek lek = null;
	
		if(!flag) {
			for(Lek l: lekovi) {
				
				if(l.getSifrarnikLekova().getNaziv().equals(naziv)) { 
					 
					lek = l;
					
					
				}
				
			}
			UpitZaLek upitZaSlanje = new UpitZaLek(lek, "nepregledan" , new Date());
			upitService.save(upitZaSlanje);
		}
			
			return flag;
		
	}
	static class Lek1{
		public String naziv;
		public Long id;
	}
	@RequestMapping(value = "/prepisiLek", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	void prepisivanjeLeka(@RequestBody Lek1 lek1, HttpServletRequest request) throws ParseException, MailException, InterruptedException {
		
		
		Pregled p = pregledService.findOne(lek1.id);
		
		
		Apoteka a = p.getApoteka();
		
		Set<Lek> lekovi = a.getLekovi();
		
		for(Lek l: lekovi) {
			
			if(l.getSifrarnikLekova().getNaziv().equals(lek1.naziv)) {
				if(l.getKolicina()>0) {
					
					l.setKolicina(l.getKolicina()-1);
					lekService.save(l);
				}
				
				
			}
			
		}
					
		
	}
	@GetMapping(value = "/zamenskiLekovi/{naziv}/{id}")
	@PreAuthorize("hasRole('DERMATOLOG') or hasRole('FARMACEUT')")
	public ResponseEntity<ArrayList<LekDTO>> zamenskiLekovi(@PathVariable String naziv, @PathVariable Long id){
		SifrarnikLekova lek = sifrarnikLekovaService.findByNaziv(naziv);
		Set<SifrarnikLekova> zamenski = new HashSet<SifrarnikLekova>();
		ArrayList<LekDTO> lekoviDTO = new ArrayList<LekDTO>();
		
		Pregled p = pregledService.findOne(id);
		
		Apoteka a = p.getApoteka();
		
		Set<Lek> lekovi = a.getLekovi();
		
		Pacijent pacijent = p.getPacijent();
		
		List<Lek> lekoviZamena = new ArrayList<Lek>();
		boolean flag = false;
		
		if(lek.getZamenskiLekovi()!=null) {
			zamenski = lek.getZamenskiLekovi();
		
		for(SifrarnikLekova sifra: zamenski) {
			  flag = false;
		  for(Lek l: lekovi) {
			  flag = false;
			 if(l.getSifrarnikLekova().getNaziv().equals(sifra.getNaziv())) {
				  flag = true; 
				 // break;
			 }
			 if(flag) {
				 lekoviZamena.add(l);
			 }
			 
		  }
		}
		Set<SifrarnikLekova> alergije = pacijent.getAlergija().getLekovi();
		List<Lek> konacnaLista = new ArrayList<Lek>();
		boolean flag2 = true;
		for(SifrarnikLekova sl: alergije) {
			flag2 = true;
			for(Lek l: lekoviZamena) {
				flag2 = true;
				if(l.getSifrarnikLekova().getNaziv().equals(sl.getNaziv())) {
					flag2 = false;
					//break;
				}
				if(flag2) {
					konacnaLista.add(l);
				}
			}
		}
		
		for(Lek l: konacnaLista) {
			lekoviDTO.add(new LekDTO(l));
		}
		
		}
		
		return new ResponseEntity<ArrayList<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/lekoviZaOcenjivanje")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekDTO>> lekoviZaOcenjivanje(HttpServletRequest request){
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);
		
		List<LekDTO> lekovi = new ArrayList<LekDTO>();
		
		for (Rezervacija rezervacija : p.getRezervacije()) {
			if (rezervacija.getStatus().equals("preuzeto")) {
				OcenaSifrarnikLekova ocena = ocenaSifrarnikLekovaService.findBySifrarnikLekovaIdAndPacijentId(
						rezervacija.getLek().getSifrarnikLekova().getId(), p.getId());

				LekDTO lek;
				if(ocena!=null) {
					lek= new LekDTO(rezervacija.getLek(), ocena.getVrednost());
					System.out.println(ocena.getVrednost());

				}
				else {
					System.out.println("usao u else?");

					lek= new LekDTO(rezervacija.getLek(), 0);

				}
				if(!lekovi.contains(lek)) {
					lekovi.add(lek);
				}
			}
		}

		return new ResponseEntity<List<LekDTO>>(lekovi, HttpStatus.OK);
	}
	
	@GetMapping(value = "/lekoviApoteke")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<String>> lekoviApoteke (HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<String> lekovi = new ArrayList<String>();
		Apoteka apoteka= admin.getApoteka();
		
		for(Lek l:apoteka.getLekovi()) {
			if(!lekovi.contains(l.getSifrarnikLekova().getNaziv())) {
				lekovi.add(l.getSifrarnikLekova().getNaziv());
			}
		}
		
		return new ResponseEntity<List<String>>(lekovi,HttpStatus.OK);
	}
	

	@GetMapping(value = "/lekovi_Apoteke")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekDTO>> lekovi_Apoteke (HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<LekDTO> lekovi = new ArrayList<LekDTO>();
		Apoteka apoteka= admin.getApoteka();
		
		for(Lek l:apoteka.getLekovi()) {
			double ocena=0;
			double brOcena=0;
			if(!l.getSifrarnikLekova().getOceneLekova().isEmpty()) {
				for(OcenaSifrarnikLekova ocenaLeka:l.getSifrarnikLekova().getOceneLekova()) {
					ocena+=ocenaLeka.getVrednost();
					brOcena++;
				}
			}
			ocena=ocena/brOcena;
			lekovi.add(new LekDTO(l,ocena, l.getKolicina()));
		}
		
		return new ResponseEntity<List<LekDTO>>(lekovi,HttpStatus.OK);
	}
	
	@PostMapping(value = "/brisanje/{id}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> brisanje (@PathVariable Long id,HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();

		Lek lek = lekService.findById(id);
		
		System.out.println(rezervacijaService.findByStatusAndLekId("izdavanje",lek.getId()).isEmpty());

		
		for(Rezervacija r:rezervacijaService.findByStatusAndLekId("izdavanje",lek.getId())) {
			System.out.println(r.getStatus());
		}
		if ((rezervacijaService.findByStatusAndLekId("izdavanje",lek.getId()).isEmpty())) { //ako nema rezervacija koje su u statusu izdavanje
			System.out.println("zasto si usao");
			apoteka.getLekovi().remove(lek);
			lek.setApoteka(null);
			lekService.save(lek);
			return new ResponseEntity<String>("Uspesno ste uklonili lek", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Lek jos nije preuzet", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/lekoviZaDodavanje")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<String>> lekoviZaDodavanje (HttpServletRequest request){
	
		List<String> lekovi= new ArrayList<String>();
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();

		
		for (SifrarnikLekova sl:sifrarnikLekovaService.findAll()) {
			int br=0;
			for(Lek lek:apoteka.getLekovi()) {
				
				if(!lek.getSifrarnikLekova().getNaziv().equals(sl.getNaziv())) {
					br++;
				}
				
			}
			if(br==apoteka.getLekovi().size()) {
				System.out.println(sl.getNaziv());
				lekovi.add(sl.getNaziv());
			}
		}
		return new ResponseEntity<List<String>>(lekovi, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/noviLek")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> noviLek (@RequestBody NoviLekDTO noviLek,HttpServletRequest request){
	
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
		Lek lek= new Lek(noviLek.getKolicina(), apoteka, sifrarnikLekovaService.findByNaziv(noviLek.getNaziv()));
		apoteka.getLekovi().add(lek);
		lekService.save(lek);
		return new ResponseEntity<String> ("Uspesno ste dodali novi lek u apoteku", HttpStatus.OK);
	}
	
	@PostMapping(value = "/promeniLek")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> promeniLek (@RequestBody NoviLekDTO lek,HttpServletRequest request){
	
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka= admin.getApoteka();
		
		for(Lek l:apoteka.getLekovi()) {
			if(l.getSifrarnikLekova().getNaziv().equals(lek.getNaziv())) {
				l.setKolicina(l.getKolicina()+lek.getKolicina());
				lekService.save(l);
				return new ResponseEntity<String> ("Uspesno ste izmenili lek", HttpStatus.OK);

			}
		}
		return null;
	}
}
