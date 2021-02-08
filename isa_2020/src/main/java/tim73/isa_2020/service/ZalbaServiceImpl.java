package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Zalba;
import tim73.isa_2020.repository.ZalbaRepository;

@Service
public class ZalbaServiceImpl implements ZalbaService{

	@Autowired
	private ZalbaRepository zalbaRepository;
	
	@Override
	public List<Zalba> findAll() {
		
		return zalbaRepository.findAll();
	}

	@Override
	public Zalba findOne(Long id) {
		
		return zalbaRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Zalba zalba) {
		
	    zalbaRepository.save(zalba);
	}

}
