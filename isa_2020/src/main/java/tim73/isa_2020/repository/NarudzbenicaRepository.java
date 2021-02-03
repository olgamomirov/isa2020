package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Narudzbenica;
import tim73.isa_2020.model.Pregled;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long>{

	@Query("SELECT n FROM Narudzbenica n WHERE n.status='ceka ponude'")
	List<Narudzbenica> proveraDatumaDavanjaPonuda();
}
