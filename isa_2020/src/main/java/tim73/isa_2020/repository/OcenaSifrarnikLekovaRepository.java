package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.OcenaSifrarnikLekova;
@Repository
public interface OcenaSifrarnikLekovaRepository extends JpaRepository<OcenaSifrarnikLekova, Long> {

	OcenaSifrarnikLekova findBySifrarnikLekovaIdAndPacijentId (Long sifrarnikLekovaId, Long pacijentId);
	
	List<OcenaSifrarnikLekova> findBySifrarnikLekovaId (Long sifrarnikLekovaId);
}
