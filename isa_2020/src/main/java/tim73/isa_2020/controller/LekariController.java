package tim73.isa_2020.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim73.isa_2020.dto.LekarDTO;
import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.model.OcenaDermatolog;
import tim73.isa_2020.model.OcenaFarmaceut;
import tim73.isa_2020.service.DermatologService;
import tim73.isa_2020.service.FarmaceutService;

@RestController
@RequestMapping(value = "/lekari")
public class LekariController {

	@Autowired
	private FarmaceutService farmaceutService;
	
	@Autowired
	private DermatologService dermatologService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<LekarDTO>> pretragaLekara(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime,HttpServletRequest request){
		

		List<Farmaceut> farmaceuti=farmaceutService.findByImeIPrezime(ime, prezime);
		List<LekarDTO> lekariDTO= new ArrayList<LekarDTO>();
		for(Farmaceut farmaceut:farmaceuti) {
			double ocena=0;
			double brOcena=0;
			if(!farmaceut.getOceneFarmaceuta().isEmpty()) {
				for(OcenaFarmaceut of:farmaceut.getOceneFarmaceuta()) {
					ocena+=of.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			lekariDTO.add(new LekarDTO(farmaceut.getId(), farmaceut.getIme()+" "+farmaceut.getPrezime(), "farmaceut", ocena, farmaceut.getApoteka().getNaziv()));
		}
		
		
		List<Dermatolog> dermatolozi=dermatologService.findByImeIPrezime(ime, prezime);
		for(Dermatolog dermatolog:dermatolozi) {
			String apoteke="";
			for(Apoteka apoteka:dermatolog.getApoteke()) {
				apoteke+=apoteka.getNaziv()+",";
			}
			apoteke=apoteke.substring(0, apoteke.length()-1); //da bih uklonila poslednji zarez
			double ocena=0;
			double brOcena=0;
			if(!dermatolog.getOceneDermatologa().isEmpty()) {
				for(OcenaDermatolog od:dermatolog.getOceneDermatologa()) {
					ocena+=od.getVrednost();
					brOcena++;
				}
				ocena=ocena/brOcena;
			}
			lekariDTO.add(new LekarDTO(dermatolog.getId(), dermatolog.getIme()+" "+dermatolog.getPrezime(), "dermatolog", ocena, apoteke));
		}
		
		return new ResponseEntity<List<LekarDTO>>(lekariDTO, HttpStatus.OK);
	}
	
}
