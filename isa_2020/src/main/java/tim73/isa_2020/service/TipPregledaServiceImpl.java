package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.TipPregleda;
import tim73.isa_2020.repository.TipPregledaRepository;

@Service
public class TipPregledaServiceImpl implements TipPregledaService{

	@Autowired
	private TipPregledaRepository tipRepository;
	
	@Override
	public void save(TipPregleda tip) {
		
		tipRepository.save(tip);
		
	}

	@Override
	public List<TipPregleda> findAll() {
		return tipRepository.findAll();
	}

	@Override
	public List<TipPregleda> findByTip(String tip) {
		return tipRepository.findByTip(tip);
	}

	@Override
	public TipPregleda findByTipAndApotekaId(String tip, Long idApoteke) {
		return tipRepository.findByTipAndApotekaId(tip, idApoteke);
	}

}
