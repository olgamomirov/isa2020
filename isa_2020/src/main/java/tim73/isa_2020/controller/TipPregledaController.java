package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.PonudaDTO;
import tim73.isa_2020.dto.TipPregledaDTO;
import tim73.isa_2020.model.AdministratorApoteke;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.Ponuda;
import tim73.isa_2020.model.TipPregleda;
import tim73.isa_2020.securityService.TokenUtils;
import tim73.isa_2020.service.KorisnikServiceImpl;
import tim73.isa_2020.service.TipPregledaService;

@RestController
@RequestMapping(value = "/tipoviPregleda")
public class TipPregledaController {

	@Autowired
	private TipPregledaService tipService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikServiceImpl userDetailsService;
	
	
	@GetMapping(value = "/svi") 
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<TipPregledaDTO>> findAll(HttpServletRequest request) {
		
		List<TipPregleda> tipovi = tipService.findAll();
		
		List<TipPregledaDTO> dto = new ArrayList<TipPregledaDTO>();
		
		for(TipPregleda t: tipovi) {
			dto.add(new TipPregledaDTO(t));
		}
		
		return new ResponseEntity<List<TipPregledaDTO>>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/tipoviPregleda") 
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<List<Double>> tipoviUApoteci(HttpServletRequest request) {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka=admin.getApoteka();
		
		double cenaKodDermatologa=0;
		double cenaKodFarmaceuta=0;
		
		
		TipPregleda tpd=tipService.findByTipAndApotekaId("dermatolog", apoteka.getId());
		TipPregleda tpf=tipService.findByTipAndApotekaId("farmaceut", apoteka.getId());

		List<Double> cene= new ArrayList<Double>();
		
		if (tpd != null) {
			cenaKodDermatologa = tpd.getCena();
		}
		if (tpf != null) {
			cenaKodFarmaceuta = tpf.getCena();
		}
		System.out.println(cenaKodDermatologa);
		System.out.println(cenaKodFarmaceuta);

		cene.add(cenaKodDermatologa);
		cene.add(cenaKodFarmaceuta);
		return new ResponseEntity<List<Double>>(cene, HttpStatus.OK);
	}
	
	@PostMapping(value="/novTip")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> novTip(@RequestBody TipPregledaDTO novTip,HttpServletRequest request) {
		
		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		AdministratorApoteke admin = (AdministratorApoteke) this.userDetailsService.loadUserByUsername(username);
		Apoteka apoteka=admin.getApoteka();
		
		if(tipService.findByTipAndApotekaId(novTip.getTip(), apoteka.getId())!=null && tipService.findByTipAndApotekaId(novTip.getTip(), apoteka.getId()).getCena()!=0) {
			return new ResponseEntity<String>("Vec ste definisali cenu pregleda kod "+novTip.getTip(), HttpStatus.BAD_REQUEST);
		}
		
		int poeni=0;
		for(TipPregleda tp:tipService.findByTip(novTip.getTip())) {
			poeni=tp.getPoeni();
			break;
		}
		
		TipPregleda tip=new TipPregleda(novTip.getTip(), novTip.getCena());
		tip.setApoteka(apoteka);
		tip.setPoeni(poeni);
		
		tipService.save(tip);
		
		return new ResponseEntity<String>("Uspesno ste dodali novi tip ", HttpStatus.OK);

	}
}
