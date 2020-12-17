package tim73.isa_2020.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tim73.isa_2020.model.Farmaceut;
import tim73.isa_2020.repository.FarmaceutRepository;

@Service
public class FarmaceutServiceImpl implements FarmaceutService{

	@Autowired
	private FarmaceutRepository farmaceutRepository;
	
	@Override
	public Farmaceut findOne(Long id) {
		// TODO Auto-generated method stub
		return farmaceutRepository.findById(id).orElse(null);
	}

}
