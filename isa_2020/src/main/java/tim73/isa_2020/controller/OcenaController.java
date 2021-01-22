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

import tim73.isa_2020.model.OcenaApoteka;
import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.OcenaFarmaceut;
import tim73.isa_2020.model.OcenaSifrarnikLekova;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.FarmaceutService;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.OcenaApotekaService;
import tim73.isa_2020.service.OcenaDermatologService;
import tim73.isa_2020.service.OcenaFarmaceutService;
import tim73.isa_2020.service.OcenaSifrarnikLekovaService;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@RequestMapping(value = "/ocene")
public class OcenaController {

	@Autowired
	private OcenaDermatologService ocenaDermatologService;

	@Autowired
	private OcenaFarmaceutService ocenaFarmaceutService;
	
	@Autowired
	private OcenaApotekaService ocenaApotekaService;
	
	@Autowired
	private OcenaSifrarnikLekovaService ocenaSifrarnikLekovaService;
	
	@Autowired
	private KorisnikServiceImpl userDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private DermatologService dermatologService;
	
	@Autowired
	private FarmaceutService farmaceutService;
	
	@Autowired 
	private ApotekaService apotekaService;
	
	@Autowired 
	private SifrarnikLekovaService sifrarnikLekovaService;

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
	
	@RequestMapping(value = "/oceniFarmaceuta/{idFarmaceuta}/{ocena}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> oceniFarmaceuta(@PathVariable Long idFarmaceuta, @PathVariable int ocena,HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		System.out.println(ocena);
		if (ocenaFarmaceutService.findByFarmaceutIdAndPacijentId(idFarmaceuta, p.getId()) != null) {
			OcenaFarmaceut ocenaFarmaceut = ocenaFarmaceutService.findByFarmaceutIdAndPacijentId(idFarmaceuta,
					p.getId());
			ocenaFarmaceut.setVrednost(ocena);
			ocenaFarmaceutService.save(ocenaFarmaceut);
			return new ResponseEntity<String>("Uspesno ste promenili ocenu farmaceutu", HttpStatus.OK);
		} else {
			OcenaFarmaceut ocenaFarmaceut = new OcenaFarmaceut(ocena);
			ocenaFarmaceut.setFarmaceut(farmaceutService.findOne(idFarmaceuta));
			ocenaFarmaceut.setPacijent(p);
			ocenaFarmaceutService.save(ocenaFarmaceut);
			return new ResponseEntity<String>("Uspesno ste ocenili farmaceuta", HttpStatus.OK);

		}
	}
	
	@RequestMapping(value = "/oceniApoteku/{idApoteke}/{ocena}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> oceniApoteku(@PathVariable Long idApoteke, @PathVariable int ocena,HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		System.out.println(ocena);
		if (ocenaApotekaService.findByApotekaIdAndPacijentId(idApoteke, p.getId()) != null) {
			OcenaApoteka ocenaApoteka = ocenaApotekaService.findByApotekaIdAndPacijentId(idApoteke,
					p.getId());
			ocenaApoteka.setVrednost(ocena);
			ocenaApotekaService.save(ocenaApoteka);
			return new ResponseEntity<String>("Uspesno ste promenili ocenu apoteci", HttpStatus.OK);
		} else {
			OcenaApoteka ocenaApoteka = new OcenaApoteka(ocena);
			ocenaApoteka.setApoteka(apotekaService.findById(idApoteke));
			ocenaApoteka.setPacijent(p);
			ocenaApotekaService.save(ocenaApoteka);
			return new ResponseEntity<String>("Uspesno ste ocenili apoteku", HttpStatus.OK);

		}
	}
	
	@RequestMapping(value = "/oceniLek/{sifraLeka}/{ocena}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> oceniLek(@PathVariable Long sifraLeka, @PathVariable int ocena,HttpServletRequest request) {
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		Pacijent p = (Pacijent) this.userDetailsService.loadUserByUsername(username);

		System.out.println(ocena);
		if (ocenaSifrarnikLekovaService.findBySifrarnikLekovaIdAndPacijentId(sifraLeka, p.getId()) != null) {
			OcenaSifrarnikLekova ocenaSifrarnikLekova = ocenaSifrarnikLekovaService.findBySifrarnikLekovaIdAndPacijentId(sifraLeka,
					p.getId());
			ocenaSifrarnikLekova.setVrednost(ocena);
			ocenaSifrarnikLekovaService.save(ocenaSifrarnikLekova);
			return new ResponseEntity<String>("Uspesno ste promenili ocenu leku", HttpStatus.OK);
		} else {
			OcenaSifrarnikLekova ocenaSifrarnikLekova = new OcenaSifrarnikLekova(ocena, p, sifrarnikLekovaService.getById(sifraLeka));
			
			ocenaSifrarnikLekovaService.save(ocenaSifrarnikLekova);
			return new ResponseEntity<String>("Uspesno ste ocenili lek", HttpStatus.OK);

		}
	}
}
