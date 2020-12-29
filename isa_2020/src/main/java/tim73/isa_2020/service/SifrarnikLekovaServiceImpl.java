package tim73.isa_2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.SifrarnikLekova;
import tim73.isa_2020.repository.SifrarnikLekovaRepository;

@Service
public class SifrarnikLekovaServiceImpl implements SifrarnikLekovaService {

	@Autowired 
	private SifrarnikLekovaRepository sifrarnikLekovaRepository;
	
	@Override
	public SifrarnikLekova getById(Long id) {
		
		return sifrarnikLekovaRepository.findById(id).orElse(null);
	}

}
