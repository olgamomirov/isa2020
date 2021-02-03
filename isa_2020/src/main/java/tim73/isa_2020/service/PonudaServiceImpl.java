package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Ponuda;
import tim73.isa_2020.repository.PonudaRepository;

@Service
public class PonudaServiceImpl implements PonudaService{

	@Autowired
	private PonudaRepository ponudaRepository;
	
	@Override
	public List<Ponuda> findAll() {
		// TODO Auto-generated method stub
		return ponudaRepository.findAll();
	}

	@Override
	public Ponuda findOne(Long id) {
		// TODO Auto-generated method stub
		return ponudaRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Ponuda ponuda) {
		ponudaRepository.save(ponuda);
	}

}
