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
		return pregledRepository.findByApotekaId(id);
	}

	@Override
	public List<Pregled> findAll() {
		return pregledRepository.findAll();
	}

	@Override
	public Pregled findOne(Long id) {
		return pregledRepository.findById(id).orElse(null);
	}


	@Override
	public List<Pregled> findByApotekaIdAndStatus(Long id, String status) {
		return pregledRepository.findByApotekaIdAndStatus(id, status);
	}


	@Override
	public List<Pregled> findByFarmaceutId(Long id) {
		return pregledRepository.findByFarmaceutId(id);
	}


	@Override
	public List<Pregled> findByPacijentId(Long id) {
		return pregledRepository.findByPacijentId(id);
	}


	@Override
	public List<Pregled> proveraPregleda() {
		// TODO Auto-generated method stub
		return pregledRepository.proveraDatumaPregleda();
	}


	@Override
	public List<Pregled> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId) {
		return pregledRepository.findByFarmaceutIdAndApotekaId(farmaceutId, apotekaId);
	}


	@Override
	public List<Pregled> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId) {
		return pregledRepository.findByDermatologIdAndApotekaId(dermatologId, apotekaId);
	}


	@Override
	public void delete(List<Pregled> pregled) {
		
		pregledRepository.deleteInBatch(pregled);
	}

}
