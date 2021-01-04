package tim73.isa_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim73.isa_2020.model.SifrarnikLekova;

public interface SifrarnikLekovaRepository extends JpaRepository<SifrarnikLekova, Long>{

	SifrarnikLekova findByNaziv(String naziv);
	
}
