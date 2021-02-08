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

}
