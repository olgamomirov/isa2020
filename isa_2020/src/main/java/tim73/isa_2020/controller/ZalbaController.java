package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.controller.ZahteviZaGodisnjiController.GodisnjiRazlog;
import tim73.isa_2020.dto.ZahtevZaGodisnjiDTO;
import tim73.isa_2020.dto.ZalbaDTO;
import tim73.isa_2020.model.ZahtevZaGodisnji;
import tim73.isa_2020.model.Zalba;
import tim73.isa_2020.service.EmailService;
import tim73.isa_2020.service.ZalbaService;

@RestController
@RequestMapping(value = "/zalbe")
public class ZalbaController {
	
	@Autowired
	private ZalbaService zalbaService;
	
	@Autowired
	private EmailService mailService;
	
	@GetMapping("/sve")
	@PreAuthorize("hasRole('SISTEM')")
	public ResponseEntity<List<ZalbaDTO>> getZalbe(HttpServletRequest request){
		
		List<Zalba> zalbe = zalbaService.findAll();
		
		List<ZalbaDTO> zalbeDTO = new ArrayList<ZalbaDTO>();
		
		
		for(Zalba z: zalbe) {
			if(z.getFarmaceut()==null&&z.getDermatolog()==null) {
			zalbeDTO.add(new ZalbaDTO(z.getId(), z.getTekstZalbe(), z.getPacijent().getIme(), z.getPacijent().getPrezime(), null, null, z.getApoteka().getNaziv()));
			}else if(z.getFarmaceut()==null&&z.getApoteka()==null){
				zalbeDTO.add(new ZalbaDTO(z.getId(), z.getTekstZalbe(), z.getPacijent().getIme(), z.getPacijent().getPrezime(), z.getDermatolog().getIme(), null, null));

			}else if(z.getDermatolog()==null&&z.getApoteka()==null){
				zalbeDTO.add(new ZalbaDTO(z.getId(), z.getTekstZalbe(), z.getPacijent().getIme(), z.getPacijent().getPrezime(), null, z.getFarmaceut().getIme(), null));

			}
		}
		
		return new ResponseEntity<List<ZalbaDTO>>(zalbeDTO, HttpStatus.OK);
	}
	
	static class OdgovorBody{
		public String odgovorZaSlanje;
		public Long id; //id zalbe na koju se odgovara
	}
		@RequestMapping(value = "/odgovor", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
		@PreAuthorize("hasRole('SISTEM')")
		public void oodbijZahtev(@RequestBody  OdgovorBody odgovor, HttpServletRequest request) throws MailException, InterruptedException {
			
	       Zalba zalba = zalbaService.findOne(odgovor.id);
	       
	       zalba.setOdgovor(odgovor.odgovorZaSlanje);
	       
	       zalbaService.save(zalba);
	      
			   mailService.sendSimpleMessage(zalba.getPacijent().getEmail(), "ODGOVOR NA ZALBU ", odgovor.odgovorZaSlanje);
			   
			          
		}

}
