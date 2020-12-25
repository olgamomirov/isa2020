package tim73.isa_2020.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;

public interface PacijentService {

	Pacijent findById(Long id);
	
	void save(Pacijent pacijent);
	
	List<Pacijent> findByImeAndPrezime(String ime, String prezime);
	
	List<Pacijent> searchByImeLikeOrPrezimeLike(String ime, String prezime);
}
