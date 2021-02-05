package tim73.isa_2020.controller;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.AkcijaPromocijaDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.StavkeAkcijePromocije;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.AkcijaPromocijaService;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.LekService;

@RestController
@RequestMapping(value = "/akcijePromocije")
public class AkcijaPromocijaController {

	@Autowired
	private AkcijaPromocijaService akcijaPromocijaService;

	@Autowired
	private EmailService mailService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private KorisnikServiceImpl korisnikDetails;

	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private LekService lekService;

	@PostMapping(value = "/novaPromocija")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> novaPromocija(@RequestBody AkcijaPromocijaDTO novaAkcProm, HttpServletRequest request)
			throws MailException, InterruptedException {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke adminA = (AdministratorApoteke) this.korisnikDetails.loadUserByUsername(username);

		AkcijaPromocija akcijaPromocija = new AkcijaPromocija(novaAkcProm.getAkcijePromocije(),
				novaAkcProm.getOdKadVazi()+"T00:00:00.000+01:00" + "/" + novaAkcProm.getDoKadVazi()+"T00:00:00.000+01:00");

		Set<Lek> lek=adminA.getApoteka().getLekovi();
		Set<StavkeAkcijePromocije> lekoviNaAkciji= new HashSet<StavkeAkcijePromocije>();
		for(String naziv:novaAkcProm.getLekoviNaAkciji()) {
			System.out.println(naziv);
			for(Lek l:lek) {
				if(l.getSifrarnikLekova().getNaziv().equals(naziv)) {
					StavkeAkcijePromocije stavka=new StavkeAkcijePromocije(l);
					stavka.setAkcijaPromocija(akcijaPromocija);
					lekoviNaAkciji.add(stavka);
				}
			}
		}
		System.out.println(novaAkcProm.getOdKadVazi());
		//2021-12-09T08:00:00.000+01:00/2021-12-09T15:00:00.000+01:00
		akcijaPromocija.setApoteka(adminA.getApoteka());
		akcijaPromocija.setStavke(lekoviNaAkciji);
		akcijaPromocija.setProcenatAkcije(novaAkcProm.getProcenat());
		akcijaPromocijaService.save(akcijaPromocija);
		
		System.out.println(akcijaPromocija.getStavke().size());

		for (Pacijent p : adminA.getApoteka().getPacijenti()) {
			mailService.sendSimpleMessage(p.getEmail(), "Akcija/Promocija", novaAkcProm.getAkcijePromocije()
					+ " vazi od " + novaAkcProm.getOdKadVazi() + " do " + novaAkcProm.getDoKadVazi()+", snizenje: "+novaAkcProm.getProcenat()+"%");
		}

		return new ResponseEntity<String>("Uspesno ste dodali akciju/promociju", HttpStatus.OK);
	}

}
