package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tim73.isa_2020.model.Pacijent;
import tim73.isa_2020.model.SifrarnikLekova;

public interface SifrarnikLekovaRepository extends JpaRepository<SifrarnikLekova, Long>{

	
	SifrarnikLekova searchByNazivIgnoreCase(@Param("naziv") String naziv);
	
}
