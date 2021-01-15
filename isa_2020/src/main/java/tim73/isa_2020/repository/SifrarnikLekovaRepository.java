package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.SifrarnikLekova;

public interface SifrarnikLekovaRepository extends JpaRepository<SifrarnikLekova, Long>{

	@Query("SELECT l FROM SifrarnikLekova l WHERE lower(l.naziv) LIKE %:naziv% ")
	SifrarnikLekova searchByNazivLikeIgnoreCase(@Param("naziv") String naziv);
	
}
