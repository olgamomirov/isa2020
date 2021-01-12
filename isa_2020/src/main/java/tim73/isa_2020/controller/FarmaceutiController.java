package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.DermatologController.PasswordChanger;
import tim73.isa_2020.controller.DermatologController.StatusBody;
import tim73.isa_2020.dto.DermatologDTO;
import tim73.isa_2020.dto.FarmaceutDTO;
import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.Authority;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.KorisnikServiceImpl;

@RestController
@RequestMapping(value = "/farmaceuti")
public class FarmaceutiController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private FarmaceutService farmaceutService;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/changeData", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changeData(@RequestBody Korisnik korisnik, HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		Korisnik userEdited = ulogovan;
		if (korisnik.getIme() != null) {
			userEdited.setIme(korisnik.getIme());
		}
		if (korisnik.getPrezime() != null) {
			userEdited.setPrezime(korisnik.getPrezime());
		}
		if (korisnik.getEmail() != null) {
			userEdited.setEmail(korisnik.getEmail());
		}
		if (korisnik.getUlica() != null) {
			userEdited.setUlica(korisnik.getUlica());
		}
		if (korisnik.getGrad() != null) {
			userEdited.setGrad(korisnik.getGrad());
		}
		if (korisnik.getDrzava() != null) {
			userEdited.setDrzava(korisnik.getDrzava());
		}
		if (korisnik.getTelefon() != null) {
			userEdited.setTelefon(korisnik.getTelefon());
		}
		korisnikService.save(userEdited);

		Farmaceut f = (Farmaceut) userEdited;

		FarmaceutDTO dto = new FarmaceutDTO(f);

		return new ResponseEntity<FarmaceutDTO>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getData")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> getData(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik ulogovan = (Korisnik) this.userDetailsService.loadUserByUsername(username);

		FarmaceutDTO farmaceutDTO = new FarmaceutDTO(ulogovan);

		return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changePassword(@RequestBody PasswordChanger passwordChanger,
			HttpServletRequest request) {
		// Ocitavamo trenutno ulogovanog korisnika
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);
		;
        user.setStatus("ulogovan");
		FarmaceutDTO farmaceutDTO = new FarmaceutDTO(user);

		if (BCrypt.checkpw(passwordChanger.oldPassword, user.getLozinka())) {

			user.setLozinka(passwordEncoder.encode(passwordChanger.newPassword));
			korisnikService.save(user);
			return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.OK);
		} else {

			System.out.println("ne!");
			return new ResponseEntity<FarmaceutDTO>(farmaceutDTO, HttpStatus.NOT_MODIFIED);
		}

	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	
	@RequestMapping(value = "/change-status", method = RequestMethod.POST)
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<FarmaceutDTO> changeStatus(@RequestBody StatusBody status, HttpServletRequest request) {
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
		
		Farmaceut f = (Farmaceut) user;
		
		FarmaceutDTO dto = new FarmaceutDTO(f);
		
     return new ResponseEntity<FarmaceutDTO>(dto, HttpStatus.OK); 
	}
	
	@GetMapping(value = "/slobodni/{vreme}/{idApoteke}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> slobodniFarmaceuti(@PathVariable String vreme, @PathVariable Long idApoteke){
		String datum = vreme.split("T")[0];
		String vremeString = vreme.split("T")[1];
System.out.println(datum);
System.out.println(vremeString);
		
		DateTime vremePregleda = new DateTime(Integer.parseInt(datum.split("-")[0]), Integer.parseInt(datum.split("-")[1]),
				Integer.parseInt(datum.split("-")[2]), Integer.parseInt(vremeString.split(":")[0]), Integer.parseInt(vremeString.split(":")[1]));
		Interval noviPregled= new Interval(vremePregleda, new Period(1800000)); // pola sata za pregled
		
		ArrayList<Farmaceut> sviFarmaceutiIzApoteke = (ArrayList<Farmaceut>) farmaceutService.findByApotekaId(idApoteke);
		ArrayList<LekarDTO> slobodniFarmaceuti=new ArrayList<LekarDTO>();
		for(Farmaceut farmaceut:sviFarmaceutiIzApoteke) {
			int slobodanPregled=0;
			int slobodanUTokuRadnogVremena=0;
			for(Pregled pregled:farmaceut.getPregledi()) {
				if(pregled!=null && pregled.getApoteka().getId().equals(idApoteke) && (pregled.getInterval().contains(noviPregled)) || pregled.getInterval().overlaps(noviPregled)){
					slobodanPregled++;
				}
			}
			for(RadnoVreme rv:farmaceut.getRadnoVreme()) {
				if(rv!=null && rv.getApoteka().getId().equals(idApoteke) && rv.getInterval().contains(noviPregled)) {
					slobodanUTokuRadnogVremena++;

				}
			}
			

			if (slobodanPregled == 0 && slobodanUTokuRadnogVremena != 0) {
				
				slobodniFarmaceuti.add(new LekarDTO(farmaceut.getId(),farmaceut.getIme()+" "+farmaceut.getPrezime(), "farmaceut",0));
			}
		}
		return new ResponseEntity<List<LekarDTO>>(slobodniFarmaceuti, HttpStatus.OK);
	}


}
