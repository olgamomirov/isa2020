package tim73.isa_2020.service;

import java.util.List;

import tim73.isa_2020.model.LoyaltyProgram;

public interface LoyaltyProgramService {

	 List<LoyaltyProgram> findByOrderByPragPoenaDesc();
	 
	 void save(LoyaltyProgram lp);
	 
	 LoyaltyProgram findById(Long id);
	 
	 List<LoyaltyProgram> findAll();
	 
	 void delete(LoyaltyProgram lp);
}
