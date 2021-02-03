package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Dermatolog;

@Repository
public interface DermatologRepository extends JpaRepository<Dermatolog, Long>{

	@Query("SELECT m FROM Dermatolog m WHERE lower(m.ime) LIKE %:ime% and lower(m.prezime) LIKE %:prezime%")
	List<Dermatolog> searchByImeLikeAndPrezimeLikeIgnoreCase(@Param("ime") String ime, @Param("prezime") String prezime);
	
}
