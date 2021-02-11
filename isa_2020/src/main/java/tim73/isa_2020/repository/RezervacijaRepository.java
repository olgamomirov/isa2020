package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Lek;
import tim73.isa_2020.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long>{

	List<Rezervacija> findByPacijentId(Long id);
	
	@Query("SELECT r FROM Rezervacija r WHERE r.status='izdavanje'")
	List<Rezervacija> izdavanjeRezervacije();
	
	List<Rezervacija> findByStatusAndLekId(String status,Long lekId);
	
	List<Rezervacija> findByStatusAndApotekaId(String status,Long apotekaId);


}
