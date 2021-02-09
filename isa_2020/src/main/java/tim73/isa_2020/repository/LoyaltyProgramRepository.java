package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim73.isa_2020.model.LoyaltyProgram;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long>{

	 List<LoyaltyProgram> findByOrderByPragPoenaDesc();
}
