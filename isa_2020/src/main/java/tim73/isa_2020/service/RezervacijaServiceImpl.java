package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Rezervacija;
import tim73.isa_2020.repository.RezervacijaRepository;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Override
	public Rezervacija findOne(Long id) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Rezervacija> findAll() {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findAll();
	}

	@Override
	public List<Rezervacija> findByPacijentId(Long id) {
		// TODO Auto-generated method stub
		return rezervacijaRepository.findByPacijentId(id);
	}

	@Override
	public void save(Rezervacija rezervacija) {
		rezervacijaRepository.save(rezervacija);
	}

}
