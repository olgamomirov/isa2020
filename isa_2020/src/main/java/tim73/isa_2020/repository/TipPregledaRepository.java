package tim73.isa_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tim73.isa_2020.model.Apoteka;
import tim73.isa_2020.model.TipPregleda;

@Repository
public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long>{

	List<TipPregleda> findByTip(String tip);

	TipPregleda findByTipAndApotekaId(String tip, Long apotekaId);
	
}
