package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Dermatolog;
import tim73.isa_2020.model.Farmaceut;

@Repository
public interface FarmaceutRepository extends JpaRepository<Farmaceut, Long>{

	List<Farmaceut> findByApotekaId(Long id);
	
	@Query("SELECT m FROM Farmaceut m WHERE lower(m.ime) LIKE %:ime% and lower(m.prezime) LIKE %:prezime%")
	List<Farmaceut> searchByImeLikeAndPrezimeLikeIgnoreCase(@Param("ime") String ime, @Param("prezime") String prezime);
	
}
