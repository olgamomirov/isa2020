package tim73.isa_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim73.isa_2020.model.OcenaDermatolog;

public interface OcenaDermatologRepository extends JpaRepository<OcenaDermatolog, Long>{

	OcenaDermatolog findByDermatologIdAndPacijentId (Long dermatologId, Long pacijentId);
}
