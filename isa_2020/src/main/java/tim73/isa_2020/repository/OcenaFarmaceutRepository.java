package tim73.isa_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.OcenaFarmaceut;

@Repository
public interface OcenaFarmaceutRepository extends JpaRepository<OcenaFarmaceut, Long> {

	OcenaFarmaceut findByFarmaceutIdAndPacijentId(Long farmaceutId, Long pacijentId);
}
