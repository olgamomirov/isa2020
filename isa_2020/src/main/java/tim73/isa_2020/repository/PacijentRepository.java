package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Korisnik;
import tim73.isa_2020.model.Pacijent;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long>{


	@Query("SELECT k FROM Pacijent k where ime like %?1%")
	List<Pacijent> findByImeAndPrezime(String ime, String prezime);
	
	@Query("SELECT m FROM Pacijent m WHERE lower(m.ime) LIKE %:ime% and lower(m.prezime) LIKE %:prezime%")
	List<Pacijent> searchByImeLikeAndPrezimeLikeIgnoreCase(@Param("ime") String ime, @Param("prezime") String prezime);
	
	@Query("SELECT p FROM Pacijent p WHERE p.penal>0")
	List<Pacijent> saPenalima();

}
