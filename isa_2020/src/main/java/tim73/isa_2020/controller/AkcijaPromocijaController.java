package tim73.isa_2020.controller;

import java.awt.image.RescaleOp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.AkcijaPromocijaService;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.KorisnikServiceImpl;

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

	@PostMapping(value = "/novaPromocija")
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<String> novaPromocija(@RequestBody AkcijaPromocijaDTO novaAkcProm, HttpServletRequest request)
			throws MailException, InterruptedException {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke adminA = (AdministratorApoteke) this.korisnikDetails.loadUserByUsername(username);

		AkcijaPromocija akcijaPromocija = new AkcijaPromocija(novaAkcProm.getAkcijePromocije(),
				novaAkcProm.getDoKadVazi() + "/" + novaAkcProm.getDoKadVazi());
		akcijaPromocija.setApoteka(adminA.getApoteka());
		akcijaPromocijaService.save(akcijaPromocija);

		for (Pacijent p : adminA.getApoteka().getPacijenti()) {
			mailService.sendSimpleMessage(p.getEmail(), "Akcija/Promocija", novaAkcProm.getAkcijePromocije()
					+ " vazi od " + novaAkcProm.getOdKadVazi() + " do " + novaAkcProm.getDoKadVazi());
		}

		return null;
	}

}
