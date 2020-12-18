package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
