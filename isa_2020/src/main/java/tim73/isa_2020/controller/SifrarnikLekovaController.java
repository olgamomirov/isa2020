package tim73.isa_2020.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.LekController.Lek1;
import tim73.isa_2020.dto.SifrarnikLekovaDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.SifrarnikLekovaService;

@RestController
@RequestMapping(value = "/sifrarnik")
public class SifrarnikLekovaController {

	@Autowired
	private SifrarnikLekovaService sifrarnikLekovaService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Set<SifrarnikLekova>> zamene(@PathVariable Long id) {
		
		return new ResponseEntity<Set<SifrarnikLekova>>(sifrarnikLekovaService.getById(id).getZamenskiLekovi(),HttpStatus.OK);
	}
	
	@GetMapping(value="/sviLekovi")
	@PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('SISTEM')")
	public ResponseEntity<Set<SifrarnikLekovaDTO>> getSvi() {
		
		
		List<SifrarnikLekova> lekovi = sifrarnikLekovaService.findAll();
		Set<SifrarnikLekovaDTO> sifreLista = new HashSet<SifrarnikLekovaDTO>();
		for(SifrarnikLekova sifra: lekovi) {
		     sifreLista.add(new SifrarnikLekovaDTO(sifra));
		}
		return new ResponseEntity<Set<SifrarnikLekovaDTO>>(sifreLista,HttpStatus.OK);
	}
	
	static class sifrarnik{
		public String naziv;
		public String vrsta;
		public String oblik;
		public String sastav;
		public String proizvodjac;
		public boolean recept;
		public String dodatneNapomene;
		public int poeni;
		public List<Long> zamenskiLekovi;
	}
	@RequestMapping(value = "/noviLek", method = RequestMethod.POST, produces = "application/json" ,  consumes = "application/json")
	@PreAuthorize("hasRole('SISTEM')")
	void dodavanjeNovogLeka(@RequestBody sifrarnik lek, HttpServletRequest request) throws ParseException, MailException, InterruptedException {

		SifrarnikLekova noviLek = new SifrarnikLekova(lek.naziv, lek.vrsta, lek.oblik, lek.sastav, lek.proizvodjac, lek.recept, lek.dodatneNapomene);
		noviLek.setPoeni(lek.poeni);
		Set<SifrarnikLekova> zamenskiLekovi = new HashSet<SifrarnikLekova>();
		
		if(lek.zamenskiLekovi.size()>0) {
		for(Long id: lek.zamenskiLekovi) {
			zamenskiLekovi.add(sifrarnikLekovaService.getById(id));
		}
		
		noviLek.setZamenskiLekovi(zamenskiLekovi);
		}
			sifrarnikLekovaService.save(noviLek);	
		
	}
	
}
