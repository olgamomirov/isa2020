package tim73.isa_2020.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.ZahteviZaGodisnjiController.ZahtevBody;
import tim73.isa_2020.dto.PonudaDTO;
import tim73.isa_2020.dto.PregledDTO;
import tim73.isa_2020.dto.ZahtevZaGodisnjiDTO;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.Ponuda;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.StavkaNarudzbenice;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.NarudzbenicaService;
import tim73.isa_2020.service.PonudaService;

@RestController
@RequestMapping(value = "/ponude")
public class PonudaController {
	
	@Autowired
	private PonudaService ponudaService;
	
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private EmailService mailService;
	
	@GetMapping(value = "/{id}") //id narudzbenice cije ponude trazimo
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<PonudaDTO>> findAll(@PathVariable Long id) {
		
		Narudzbenica narudzbenica = narudzbenicaService.findOne(id);
		
		Set<Ponuda> ponude= narudzbenica.getPonude();
		
        List<PonudaDTO> ponudeDTO= new ArrayList<>();
		
		for(Ponuda p: ponude) {
			ponudeDTO.add(new PonudaDTO(p));
		}	
		
		return new ResponseEntity<List<PonudaDTO>>(ponudeDTO, HttpStatus.OK);
	}

	static class PonudaId{
		public Long id;
	}
	@RequestMapping(value = "/odobri", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<PonudaDTO> odobriPonudu(@RequestBody PonudaId ponudaId, HttpServletRequest request) throws MailException, InterruptedException {
		
		Ponuda ponuda = ponudaService.findOne(ponudaId.id);
		
		
		ponuda.setStatus("odobrena");
		
		ponudaService.save(ponuda);
		
		
		PonudaDTO ponudaDTO = new PonudaDTO(ponuda);
		
		Narudzbenica narudzbenica = ponuda.getNarudzbenica();
		
		Set<StavkaNarudzbenice> stavke = narudzbenica.getStavkeNarudzbenice();
		
		List<Lek> lekovi = new ArrayList<Lek>();
		
		for(StavkaNarudzbenice s: stavke) {
			s.getLek().setKolicina(s.getKolicina()+s.getLek().getKolicina());
			
		}
		
		narudzbenica.setStatus("obradjena");
		
		narudzbenicaService.save(narudzbenica);
		
		for(Ponuda p: narudzbenica.getPonude()) {
           if(!p.equals(ponuda)) {
			 p.setStatus("odbijena");
			 ponudaService.save(p);
			   mailService.sendSimpleMessage(p.getDobavljac().getEmail(), "ODBIJANJE PONUDE", "Vasa ponuda za narudzbenicu iz apoteke " + narudzbenica.getApoteka().getNaziv() + " je odbijena.");

           }
		}
		   mailService.sendSimpleMessage(ponuda.getDobavljac().getEmail(), "ODOBRAVANJE PONUDE", "Vasa ponuda za narudzbenicu iz apoteke " + narudzbenica.getApoteka().getNaziv() + " je odobrena.");
		   
          
		return new ResponseEntity<PonudaDTO>(ponudaDTO, HttpStatus.OK);
	}
}
