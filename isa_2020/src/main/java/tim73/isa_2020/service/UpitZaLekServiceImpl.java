package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.UpitZaLek;
import tim73.isa_2020.repository.UpitZaLekRepository;

@Service
public class UpitZaLekServiceImpl implements UpitZaLekService{

	@Autowired
	private UpitZaLekRepository upitRepository;
	
	@Override
	public void save(UpitZaLek upit) {
		
		upitRepository.save(upit);
		
	}

	@Override
	public List<UpitZaLek> findAll() {
		
		return upitRepository.findAll();
	}

}
