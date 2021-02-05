package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Pregled;
import tim73.isa_2020.model.Rezervacija;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long>{

	List<Pregled> findByApotekaId(Long id);
	
	List<Pregled> findByApotekaIdAndStatus(Long id, String status);
	
	List<Pregled> findByFarmaceutId(Long id);
	
	List<Pregled> findByPacijentId(Long id);
	
	@Query("SELECT p FROM Pregled p WHERE p.status='rezervisan' or p.status='default'")
	List<Pregled> proveraDatumaPregleda();
	
	List<Pregled> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId);
	List<Pregled> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId);

}
