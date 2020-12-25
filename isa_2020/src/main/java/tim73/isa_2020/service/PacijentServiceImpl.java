package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.repository.PacijentRepository;

@Service
public class PacijentServiceImpl implements PacijentService{

	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Override
	public Pacijent findById(Long id) {
		// TODO Auto-generated method stub
		return pacijentRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Pacijent pacijent) {
		pacijentRepository.save(pacijent);
	}
	
	public List<Pacijent> findByImeAndPrezime(String ime, String prezime) {
		List<Pacijent> u = pacijentRepository.findByImeAndPrezime(ime, prezime);
		return u;
	}

	@Override
	public List<Pacijent> searchByImeLikeOrPrezimeLike(String ime, String prezime) {
		List<Pacijent> u = pacijentRepository.searchByImeLikeAndPrezimeLikeIgnoreCase(ime, prezime);
		return u;
	}

}
