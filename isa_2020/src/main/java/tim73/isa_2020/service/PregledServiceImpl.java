package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.repository.PregledRepository;

@Service
public class PregledServiceImpl implements PregledService{

	@Autowired
	private PregledRepository pregledRepository;
	
	@Override
	public void save(Pregled pregled) {
		
		pregledRepository.save(pregled);
		
	}
	

	@Override
	public List<Pregled> findByApotekaId(Long id) {
		// TODO Auto-generated method stub
		return pregledRepository.findByApotekaId(id);
	}

	@Override
	public List<Pregled> findAll() {
		// TODO Auto-generated method stub
		return pregledRepository.findAll();
	}

	@Override
	public Pregled findOne(Long id) {
		// TODO Auto-generated method stub
		return pregledRepository.findById(id).orElse(null);
	}

}
