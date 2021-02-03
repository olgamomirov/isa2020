package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.repository.NarudzbenicaRepository;

@Service
public class NarudzbenicaServiceImpl implements NarudzbenicaService{

	@Autowired
	private NarudzbenicaRepository narudzbenicaRepository;
	
	@Override
	public void save(Narudzbenica narudzbenica) {
		
		narudzbenicaRepository.save(narudzbenica);
		
	}

	@Override
	public List<Narudzbenica> findAll() {
		
		return narudzbenicaRepository.findAll();
	}

	@Override
	public Narudzbenica findOne(Long id) {
		// TODO Auto-generated method stub
		return narudzbenicaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Narudzbenica> proveraNarudzbenice() {
		// TODO Auto-generated method stub
		return narudzbenicaRepository.proveraDatumaDavanjaPonuda();
	}

}
