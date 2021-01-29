package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.UserTokenState;
import tim73.isa_2020.repository.KorisnikRepository;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.OcenaDermatologService;
import tim73.isa_2020.service.PregledService;

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
	
	@Autowired 
	private PregledService pregledService;
	
	@Autowired
	private OcenaDermatologService ocenaDermatologService;
	
	@Autowired
	private ApotekaService apotekaService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	private KorisnikRepository rep;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "{id}")
	ResponseEntity<Dermatolog> findById(@PathVariable long id) {
		return new ResponseEntity<>(dermatologService.findById(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changeData", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> changeData(@RequestBody Korisnik korisnik, HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Korisnik userEdited = ulogovan;
		if (korisnik.getIme() != "") {
			userEdited.setIme(korisnik.getIme());
		}
		if (korisnik.getPrezime() != "") {
			userEdited.setPrezime(korisnik.getPrezime());
		}
		if (korisnik.getEmail() != "") {
			userEdited.setEmail(korisnik.getEmail());
		}
		if (korisnik.getUlica() != "") {
			userEdited.setUlica(korisnik.getUlica());
		}
		if (korisnik.getGrad() != "") {
			userEdited.setGrad(korisnik.getGrad());
		}
		if (korisnik.getDrzava() != "") {
			userEdited.setDrzava(korisnik.getDrzava());
		}
		if (korisnik.getTelefon() != "") {
			userEdited.setTelefon(korisnik.getTelefon());
		}
		korisnikService.save(userEdited);

		Dermatolog d = (Dermatolog) userEdited;

		DermatologDTO dto = new DermatologDTO(d);

		return new ResponseEntity<DermatologDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getData")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> getData(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		DermatologDTO dermatologDTO = new DermatologDTO(ulogovan);

		return new ResponseEntity<DermatologDTO>(dermatologDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> changePassword(@RequestBody PasswordChanger passwordChanger,
			HttpServletRequest request) {
		// Ocitavamo trenutno ulogovanog korisnika
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		;
        user.setStatus("ulogovan");
		DermatologDTO dermatologDTO = new DermatologDTO(user);

		if (BCrypt.checkpw(passwordChanger.oldPassword, user.getLozinka())) {

			user.setLozinka(passwordEncoder.encode(passwordChanger.newPassword));
			korisnikService.save(user);
			return new ResponseEntity<DermatologDTO>(dermatologDTO, HttpStatus.OK);
		} else {

			return new ResponseEntity<DermatologDTO>(dermatologDTO, HttpStatus.NOT_MODIFIED);
		}

	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	static class StatusBody {
		public String statusNovi;
		
	}
	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<DermatologDTO> changeStatus(@RequestBody StatusBody status, HttpServletRequest request) {
		// Ocitavamo trenutno ulogovanog korisnika
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		List<Authority> authority = (List<Authority>) user.getAuthorities();
		
		//new UserTokenState(token, tokenUtils.getExpiredIn(), authority.get(0).getName(), "ulogovan");
	
		System.out.println(status.statusNovi  + " sta cita?");
		String status1 = status.statusNovi;
		user.setStatus(status1);

		korisnikService.save(user);
	
		System.out.println(user.getStatus() + " kakooo" + user.getIme() + " " + user.getTelefon());
		
		Dermatolog d = (Dermatolog) user;
		
		DermatologDTO dto = new DermatologDTO(d);
		
     return new ResponseEntity<DermatologDTO>(dto, HttpStatus.OK); 
	}

	//pacijentu se nude dermatolozi kod kojih je bar jednom imao pregled 
	@RequestMapping(value = "/dermatoloziZaOcenjivanje", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> dermatoloziKojiSemoguOceniti(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);
		
		List<LekarDTO> dermatolozi= new ArrayList<LekarDTO>();
		
		for(Pregled pregled:pregledService.findByPacijentId(p.getId())) {
			if(pregled.getStatus().equals("odradjen")&&pregled.getDermatolog()!=null) {
				LekarDTO dermatolog;
				OcenaDermatolog ocena=ocenaDermatologService.findByDermatologIdAndPacijentId(pregled.getDermatolog().getId(), p.getId());
				if(ocena!=null) {
					dermatolog=new LekarDTO(pregled.getDermatolog().getId(),pregled.getDermatolog().getIme()+pregled.getDermatolog().getPrezime(), "dermatolog",ocena.getVrednost());
				}
				else{
					dermatolog=new LekarDTO(pregled.getDermatolog().getId(),pregled.getDermatolog().getIme()+pregled.getDermatolog().getPrezime(), "dermatolog",0);
				}
				if(!dermatolozi.contains(dermatolog)) {
					dermatolozi.add(dermatolog);
				}
			}
		}
		return new ResponseEntity<List<LekarDTO>>(dermatolozi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekarDTO>> pretragaDermatologa(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime,HttpServletRequest request){
		

		List<Dermatolog> dermatolozi=dermatologService.findByImeIPrezime(ime, prezime);
		List<LekarDTO> dermatoloziDTO= new ArrayList<LekarDTO>();
		for(Dermatolog dermatolog:dermatolozi) {
			String apoteke="";
			for(Apoteka apoteka:dermatolog.getApoteke()) {
				apoteke+=apoteka.getNaziv()+",";
			}
			apoteke=apoteke.substring(0, apoteke.length()-1); //da bih uklonila poslednji zarez
			double ocena=0;
			double brOcena=0;
			if(!dermatolog.getOceneDermatologa().isEmpty()) {
				for(OcenaDermatolog od:dermatolog.getOceneDermatologa()) {
					ocena+=od.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			dermatoloziDTO.add(new LekarDTO(dermatolog.getId(), dermatolog.getIme()+" "+dermatolog.getPrezime(), "dermatolog", ocena, apoteke));
		}
		return new ResponseEntity<List<LekarDTO>>(dermatoloziDTO, HttpStatus.OK);
	}
	@RequestMapping(value = "/dermatolozi", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<LekarDTO>> dermatolozi(HttpServletRequest request){
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		
		List<LekarDTO> dermatoloziDTO= new ArrayList<LekarDTO>();
		
		Apoteka a=admin.getApoteka();
		
		for(Dermatolog dermatolog:a.getDermatolozi()) {
			String apoteke="";
			for(Apoteka apoteka:dermatolog.getApoteke()) {
				apoteke+=apoteka.getNaziv()+",";
			}
			apoteke=apoteke.substring(0, apoteke.length()-1); //da bih uklonila poslednji zarez
			double ocena=0;
			double brOcena=0;
			if(!dermatolog.getOceneDermatologa().isEmpty()) {
				for(OcenaDermatolog od:dermatolog.getOceneDermatologa()) {
					ocena+=od.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			dermatoloziDTO.add(new LekarDTO(dermatolog.getId(), dermatolog.getIme()+" "+dermatolog.getPrezime(), "dermatolog", ocena, apoteke));
		}
		return new ResponseEntity<List<LekarDTO>>(dermatoloziDTO, HttpStatus.OK);
	}
}
	
