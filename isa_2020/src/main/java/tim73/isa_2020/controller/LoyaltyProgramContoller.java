package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.LoyaltyProgramDTO;
import tim73.isa_2020.model.LoyaltyProgram;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.TipPregleda;
import tim73.isa_2020.service.KorisnikService;
import tim73.isa_2020.service.LoyaltyProgramService;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.TipPregledaService;

@RestController
@RequestMapping(value = "/programi")
@CrossOrigin(origins = "http://localhost:3000")
public class LoyaltyProgramContoller {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@Autowired
	private TipPregledaService tipPregledaService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	@PostMapping(value="/novi")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<String> noviProgram(@RequestBody LoyaltyProgramDTO novi){
		LoyaltyProgram lp=new LoyaltyProgram(novi.getPragPoena(), novi.getKategorijaKorisnika(), novi.getPopust());
		loyaltyProgramService.save(lp);
		return new ResponseEntity<String>("Uspesno ste dodali novi program", HttpStatus.OK);
	}
	
	@GetMapping(value="/svi")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<List<LoyaltyProgramDTO>> sviProgrami(){
		List<LoyaltyProgramDTO> programi= new ArrayList<LoyaltyProgramDTO>();
		for(LoyaltyProgram lp:loyaltyProgramService.findAll()) {
			if(lp.getId()!=1) {
				programi.add(new LoyaltyProgramDTO(lp.getId(), lp.getKategorijaKorisnika(), lp.getPopust(), lp.getPragPoena()));
			}
		}
		return new ResponseEntity<List<LoyaltyProgramDTO>>(programi, HttpStatus.OK);
	}
	
	@GetMapping(value="/zaIzmenu/{id}")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<LoyaltyProgramDTO> sviProgrami(@PathVariable Long id){
		LoyaltyProgram lp=loyaltyProgramService.findById(id);
		LoyaltyProgramDTO lpDTO=new LoyaltyProgramDTO(lp.getId(), lp.getKategorijaKorisnika(), lp.getPopust(), lp.getPragPoena());
		return new ResponseEntity<LoyaltyProgramDTO>(lpDTO, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value="/izmena")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<String> izmeniProgram(@RequestBody LoyaltyProgramDTO izmenjen){
		LoyaltyProgram lp=loyaltyProgramService.findById(izmenjen.getId());
		lp.setKategorijaKorisnika(izmenjen.getKategorijaKorisnika());
		lp.setPopust(izmenjen.getPopust());
		lp.setPragPoena(izmenjen.getPragPoena());
		loyaltyProgramService.save(lp);
		return new ResponseEntity<String>("Uspesno ste izmenili program", HttpStatus.OK);
	}
	
	@GetMapping(value="/poeni")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<List<String>> poeni(){
		String poeniD="";
		String poeniF="";
		for(TipPregleda tp:tipPregledaService.findAll()) {
			if(tp.getTip().equals("dermatolog")) {
				poeniD=String.valueOf(tp.getPoeni());
			}
			else {
				poeniF=String.valueOf(tp.getPoeni());

			}
		}
		List<String> poeni= new ArrayList<String>();
		poeni.add(poeniD);
		poeni.add(poeniF);
		return new ResponseEntity<List<String>>(poeni, HttpStatus.OK);
	}
	
	@PutMapping(value="/izmenaPoena")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<String> izmeniPoene(@RequestBody String poeniIzmena){
		String tip=poeniIzmena.split(",")[0];
		int poeni=Integer.parseInt(poeniIzmena.split(",")[1]);
		for(TipPregleda tp:tipPregledaService.findByTip(tip)) {
			tp.setPoeni(poeni);
			tipPregledaService.save(tp);
		}
		return new ResponseEntity<String>("Uspesno ste izmenili poene", HttpStatus.OK);
	}
	
	@PutMapping(value="/brisanje/{id}")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<String> brisanje(@PathVariable Long id){
		LoyaltyProgram lp=loyaltyProgramService.findById(id);
		for(Pacijent p:pacijentService.findByLoyaltyProgramId(id)) {
			p.setLoyaltyProgram(null);
		}
		
		loyaltyProgramService.delete(lp);
		
		
		for(Pacijent p:pacijentService.findAll()) {
			for(LoyaltyProgram l:loyaltyProgramService.findByOrderByPragPoenaDesc()) {
				if(p.getPoeni()>l.getPragPoena()) {
					p.setLoyaltyProgram(l);
					korisnikService.save(p);
					break;
				}
			}
		}
		
		return new ResponseEntity<String>("Uspesno ste obrisali program", HttpStatus.OK);
	}

}
