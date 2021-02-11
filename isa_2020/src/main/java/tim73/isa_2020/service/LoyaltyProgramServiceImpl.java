package tim73.isa_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim73.isa_2020.model.LoyaltyProgram;
import tim73.isa_2020.repository.LoyaltyProgramRepository;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {

	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	 
	@Override
	public List<LoyaltyProgram> findByOrderByPragPoenaDesc() {
		return loyaltyProgramRepository.findByOrderByPragPoenaDesc();
	}

	@Override
	public void save(LoyaltyProgram lp) {
		loyaltyProgramRepository.save(lp);
	}

	@Override
	public LoyaltyProgram findById(Long id) {
		return loyaltyProgramRepository.getOne(id);
	}

	@Override
	public List<LoyaltyProgram> findAll() {
		return loyaltyProgramRepository.findAll();
	}

	@Override
	public void delete(LoyaltyProgram lp) {
		loyaltyProgramRepository.delete(lp);
	}

}
