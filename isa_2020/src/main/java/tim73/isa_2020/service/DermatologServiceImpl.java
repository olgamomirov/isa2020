package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.repository.DermatologRepository;

@Service
public class DermatologServiceImpl implements DermatologService {

	@Autowired
	private DermatologRepository dermatologRepository;
	
	
	
	@Override
	public Dermatolog findById(long id) {
	
		return dermatologRepository.findById(id).orElse(null);
		
	}



	@Override
	public void save(Dermatolog dermatolog) {
		dermatologRepository.save(dermatolog);
		
	}



	@Override
	public List<Dermatolog> findByImeIPrezime(String ime, String prezime) {
		return dermatologRepository.searchByImeLikeAndPrezimeLikeIgnoreCase(ime, prezime);
	}



	@Override
	public List<Dermatolog> findAll() {
		return dermatologRepository.findAll();
	}

}
