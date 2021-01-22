package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.OcenaApoteka;

@Repository
public interface OcenaApotekaRepository  extends JpaRepository<OcenaApoteka, Long>{

	OcenaApoteka findByApotekaIdAndPacijentId(Long apotekaId, Long pacijentId);
	
	List<OcenaApoteka> findByApotekaId(Long id);
}
