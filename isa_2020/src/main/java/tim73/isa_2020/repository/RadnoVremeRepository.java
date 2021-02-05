package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.RadnoVreme;

@Repository
public interface RadnoVremeRepository extends JpaRepository<RadnoVreme, Long>{

	List<RadnoVreme> findByFarmaceutIdAndApotekaId(Long farmaceutId, Long apotekaId);
	List<RadnoVreme> findByDermatologIdAndApotekaId(Long dermatologId, Long apotekaId);

}
