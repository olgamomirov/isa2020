package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.Cenovnik;
import tim73.isa_2020.repository.CenovnikRepository;

@Service
public class CenovnikServiceImpl implements CenovnikService{

	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	@Override
	public Cenovnik findOne(Long id) {
		// TODO Auto-generated method stub
		return cenovnikRepository.findById(id).orElse(null);
	}

	@Override
	public List<Cenovnik> findAll() {
		// TODO Auto-generated method stub
		return cenovnikRepository.findAll();
	}

	@Override
	public void save(Cenovnik cenovnik) {
		// TODO Auto-generated method stub
		cenovnikRepository.save(cenovnik);
	}

}
