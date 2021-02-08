package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.ApotekaDTO;
import tim73.isa_2020.dto.LekDTO;
import tim73.isa_2020.model.AkcijaPromocija;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.model.CenovnikStavka;
import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.OcenaApoteka;
import tim73.isa_2020.model.OcenaSifrarnikLekova;
import tim73.isa_2020.model.StavkeAkcijePromocije;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.LekService;
import tim73.isa_2020.service.OcenaApotekaService;
import tim73.isa_2020.service.OcenaSifrarnikLekovaService;

@RestController
@RequestMapping(value = "/svi")
public class NeautentifikovaniController {
//proba
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private OcenaSifrarnikLekovaService ocenaSifrarnikLekovaService;
	
	@GetMapping(value = "/lekovi/{id}")
	public ResponseEntity<List<LekDTO>> lekoviApoteke(@PathVariable Long id) {
		
		List<Lek> lekovi= lekService.findByApotekaId(id);
		
		java.util.Date juDate = new Date();
	    DateTime dt = new DateTime(juDate);
		Apoteka apoteka=apotekaService.findById(id);
		
		
		
		
		System.out.println(dt.toString());
		
        List<LekDTO> lekoviDTO= new ArrayList<>();
		
		for (Lek l : lekovi) {
			double cena=0;

			double ocena = 0;
			double brojOcena = 0;
			if (!ocenaSifrarnikLekovaService.findBySifrarnikLekovaId(l.getSifrarnikLekova().getId()).isEmpty()) {
				for (OcenaSifrarnikLekova ocenaLeka : ocenaSifrarnikLekovaService
						.findBySifrarnikLekovaId(l.getSifrarnikLekova().getId())) {
					ocena += ocenaLeka.getVrednost();
					brojOcena++;
				}
				ocena = ocena / brojOcena;
			}
			
			for(Cenovnik cenovnik:apoteka.getCenovnici()) {
				Interval interval=new Interval(cenovnik.getInterval());
				if (dt.isAfter(interval.getStart())&& dt.isBefore(interval.getEnd())) {
					for(CenovnikStavka cs:cenovnik.getStavkeCenovnika()) {
						if(cs.getLek().equals(l)) {
							cena=cs.getCena();
						}
					}
				}
			}
			
			for(AkcijaPromocija ap:apoteka.getAkcijePromocije()) {
				Interval interval=new Interval(ap.getVremeVazenja());

				if (dt.isAfter(interval.getStart())&& dt.isBefore(interval.getEnd())) {
					for(StavkeAkcijePromocije aps:ap.getStavke()) {
						if(aps.getLek().equals(l)) {
							cena=cena*((100-ap.getProcenatAkcije())/100);
						}
					}
				}
			}
			
			
			lekoviDTO.add(new LekDTO(l, ocena,cena));
		}
		
		
		return new ResponseEntity<List<LekDTO>>(lekoviDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/apoteke")
	public ResponseEntity<List<ApotekaDTO>> findAll() {
		
		List<Apoteka> apoteke= apotekaService.findAll();
		
		List<ApotekaDTO> apotekeDTO= new ArrayList<>();
		
		for(Apoteka a:apoteke) {
			double ocena=0;

			double brojOcena = 0;

			if (!a.getOceneApoteke().isEmpty()) {
				for (OcenaApoteka oa : a.getOceneApoteke()) {

					if (oa != null) {
						ocena += oa.getVrednost();
						brojOcena++;
					}
				}
				ocena = ocena / brojOcena;
			}
			apotekeDTO.add(new ApotekaDTO(a,ocena));
		}
		
		return new ResponseEntity<List<ApotekaDTO>>(apotekeDTO, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/gradovi")
	public ResponseEntity<List<String>> gradoviZaFilter(){
		ArrayList<String> gradovi= new ArrayList<String>();
		for(Apoteka a: apotekaService.findAll()) {
			if(!gradovi.contains(a.getGrad())) {
				gradovi.add(a.getGrad());
			}
		}
		return new ResponseEntity<List<String>>(gradovi, HttpStatus.OK);
	}
	
}
