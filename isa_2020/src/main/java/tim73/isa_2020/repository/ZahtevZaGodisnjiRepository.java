package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.ZahtevZaGodisnji;

@Repository
public interface ZahtevZaGodisnjiRepository extends JpaRepository<ZahtevZaGodisnji, Long>{

	 List<ZahtevZaGodisnji> findByApotekaId(Long id);
}
