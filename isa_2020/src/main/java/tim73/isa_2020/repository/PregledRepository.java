package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Pregled;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long>{

	List<Pregled> findByApotekaId(Long id);
	
	List<Pregled> findByApotekaIdAndStatus(Long id, String status);
	
	List<Pregled> findByFarmaceutId(Long id);
}
