package tim73.isa_2020;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.service.PacijentService;
import tim73.isa_2020.service.RezervacijaService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private RezervacijaService rezervacijaService;

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
}
