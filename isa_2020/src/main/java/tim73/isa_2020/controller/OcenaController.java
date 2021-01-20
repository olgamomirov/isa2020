package tim73.isa_2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.OcenaDermatologService;

@RestController
@RequestMapping(value = "/ocene")
public class OcenaController {

	@Autowired
	private OcenaDermatologService ocenaDermatologService;

	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private DermatologService dermatologService;

	@RequestMapping(value = "/oceniDermatologa/{idDermatologa}/{ocena}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> oceniDermatologa(@PathVariable Long idDermatologa, @PathVariable int ocena,HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		System.out.println(ocena);
		if (ocenaDermatologService.findByDermatologIdAndPacijentId(idDermatologa, p.getId()) != null) {
			OcenaDermatolog ocenaDermatolog = ocenaDermatologService.findByDermatologIdAndPacijentId(idDermatologa,
					p.getId());
			ocenaDermatolog.setVrednost(ocena);
			ocenaDermatologService.save(ocenaDermatolog);
			return new ResponseEntity<String>("Uspesno ste promenili ocenu dermatologu", HttpStatus.OK);
		} else {
			OcenaDermatolog ocenaDermatolog = new OcenaDermatolog(ocena);
			ocenaDermatolog.setDermatolog(dermatologService.findById(idDermatologa));
			ocenaDermatolog.setPacijent(p);
			ocenaDermatologService.save(ocenaDermatolog);
			return new ResponseEntity<String>("Uspesno ste ocenili dermatologa", HttpStatus.OK);

		}
	}
}
