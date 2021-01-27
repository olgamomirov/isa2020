package tim73.isa_2020;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.RadnoVreme;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.service.ApotekaService;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.PregledService;
import tim73.isa_2020.service.RadnoVremeService;
import tim73.isa_2020.service.RezervacijaService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private PregledService pregledService;
	
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private RadnoVremeService radnoVremeService;
	
	@Autowired
	private DermatologService dermatologService;

	//@Scheduled(cron = "00 00 1 * ?") //u ponoc svakog prvog u mesecu
	@Scheduled(fixedRate = 120000) // update na 2 min
	public void obrisiPenale() {
		for(Pacijent p:pacijentService.saPenalima()) {
			p.setPenal(0);
			pacijentService.save(p);
		}
	}
	
	@Scheduled(fixedRate = 60000) // update na 1 min
	public void penaliZaNeispostovanuRezervacijuLeka() {
		for(Rezervacija r:rezervacijaService.izdavanjeRezervacije()) {
			DateTime vremeRezervacije= new DateTime(r.getDatumPreuzimanja());
			if((vremeRezervacije.getMillis()-System.currentTimeMillis())<0){
				Pacijent p=r.getPacijent();
				p.setPenal(p.getPenal()+1);
				pacijentService.save(p);
				r.setStatus("otkazana");
				rezervacijaService.save(r);
				
			}
		}
	}
	@Scheduled(fixedRate = 60000)
	public void cronJob() {
		
		
		for(Pregled p: pregledService.proveraPregleda()) {
			DateTime vremePregleda= new DateTime(p.getInterval().split("/")[0]);
			
				if(((vremePregleda.getMillis()-System.currentTimeMillis())<0)) {


					p.setStatus("neodradjen");
					if(p.getPacijent()!=null) {
					if(p.getPacijent().getPenal()!=0) {
					p.getPacijent().setPenal(p.getPacijent().getPenal()+1);
					pacijentService.save(p.getPacijent());
					pregledService.save(p);	
					
					}else {
						p.getPacijent().setPenal(1);	
						pacijentService.save(p.getPacijent());
						pregledService.save(p);	
						
					}
					vremePregleda=null;
					}else {
						p.setStatus("neodradjen");
						pregledService.save(p);	
					}
					
				}
					
					
			
			
			
		}
		
		
	}
	@Scheduled(initialDelay = 5000, fixedDelay=Long.MAX_VALUE)//izvrsava se posle 5 sekundi i samo jednom
	void add(){
		Dermatolog dermatolog= dermatologService.findById(2);
		Apoteka apoteka= apotekaService.findById(1);
		RadnoVreme rv = null;
		
		for(int j=1; j<=12; j++) {
		
	      for(int i=1; i<28; i++) {
	    	  if(i<=10) {
		  rv=new RadnoVreme("2021-" + "0" + j + "-" + i + "T08:00:00.000+01:00/2021-" + "0" +j + "-" + i + "T15:00:00.000+01:00");
		  rv.setDermatolog(dermatolog);
		  rv.setApoteka(apoteka);
		  radnoVremeService.save(rv);
		  }else {
			  rv=new RadnoVreme("2021-" + j + "-" + i + "T08:00:00.000+01:00/2021-" +j + "-" + i + "T15:00:00.000+01:00");
			  rv.setDermatolog(dermatolog);
			  rv.setApoteka(apoteka);
			  radnoVremeService.save(rv);
			  
		  }
	    	  
	}
		}
		
		
		
	
	}
}