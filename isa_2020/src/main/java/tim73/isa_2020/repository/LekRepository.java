package tim73.isa_2020.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Lek;

@Repository
public interface LekRepository extends JpaRepository<Lek, Long> {

	List<Lek> findByApotekaId(Long id);
	
	Set<Lek> findByNaziv(String naziv);
}
